package com.abhishekvermaa10.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import com.abhishekvermaa10.event.MailEvent;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig {
	
	private final KafkaProperties kafkaProperties;
	
	@Bean
	ConcurrentKafkaListenerContainerFactory<String, MailEvent> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, MailEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setObservationEnabled(true);
		return factory;
	}
	
	@Bean
	ConsumerFactory<String, MailEvent> consumerFactory() {
		Map<String, Object> configProps = kafkaProperties.buildConsumerProperties();
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
		configProps.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "com.abhishekvermaa10.event");
		return new DefaultKafkaConsumerFactory<>(configProps);
	}

}
