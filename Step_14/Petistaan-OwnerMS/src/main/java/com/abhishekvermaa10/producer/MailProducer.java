package com.abhishekvermaa10.producer;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.event.MailEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author abhishekvermaa10
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MailProducer {
	
	private final KafkaTemplate<String, MailEvent> kafkaTemplate;
	@Value("${app.kafka.topic.mail-events}")
	private String mailTopic;
	
	public String publishEvent(MailDTO mailDTO) {
		MailEvent mailEvent = new MailEvent(UUID.randomUUID().toString(), mailDTO, Instant.now());
		log.info("Publishing event: {}", mailEvent);
		kafkaTemplate.send(mailTopic, mailEvent.eventId(), mailEvent)
		.whenComplete((result, ex) -> {
			if (ex != null) {
				log.error("Failed to send event {}", mailEvent.eventId(), ex);
			} else {
				var metadata = result.getRecordMetadata();
				log.info("Event sent successfully: {} to topic {}, partition {}, offset {}", mailEvent.eventId(),
						metadata.topic(), metadata.partition(), metadata.offset());
			}
		});
		return mailEvent.eventId();
	}

}
