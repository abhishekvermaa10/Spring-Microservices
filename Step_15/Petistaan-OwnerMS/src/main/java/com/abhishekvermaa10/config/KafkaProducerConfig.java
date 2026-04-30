package com.abhishekvermaa10.config;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import com.abhishekvermaa10.event.MailEvent;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig {
	
	private final KafkaProperties kafkaProperties;
	
	@Bean
	KafkaTemplate<String, MailEvent> kafkaTemplate() {
		KafkaTemplate<String, MailEvent> template = new KafkaTemplate<>(producerFactory());
		template.setObservationEnabled(true);
		return template;
	}
	
	@Bean
	ProducerFactory<String, MailEvent> producerFactory() {
		Map<String, Object> configProps = kafkaProperties.buildProducerProperties();
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

}
