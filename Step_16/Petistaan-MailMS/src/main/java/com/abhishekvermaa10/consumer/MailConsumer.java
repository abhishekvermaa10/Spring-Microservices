package com.abhishekvermaa10.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.abhishekvermaa10.event.MailEvent;
import com.abhishekvermaa10.service.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author abhishekvermaa10
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MailConsumer {

	private final MailService mailService;

	@KafkaListener(topics = "${app.kafka.topic.mail-events}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeEvent(MailEvent mailEvent) {
		try {
			log.info("Received event: {}", mailEvent);
			String response = mailService.sendEmail(mailEvent.mailDTO());
			log.info("Mail sent for event {}: {}", mailEvent.eventId(), response);
		} catch (Exception ex) {
			log.error("Error processing event {}", mailEvent.eventId(), ex);
			throw ex;
		}
	}

}
