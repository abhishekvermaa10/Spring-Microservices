package com.abhishekvermaa10.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.abhishekvermaa10.client.PetClient;

import io.micrometer.observation.ObservationRegistry;

/**
 * @author abhishekvermaa10
 */
@Configuration
public class HttpServiceClientConfig {

	@Value("${pet.service.base.url}")
	private String petServiceBaseUrl;
	
	@LoadBalanced
	@Bean
	RestClient.Builder restClientBuilder(ObservationRegistry observationRegistry) {
		return RestClient.builder()
				.observationRegistry(observationRegistry);
	}
	
	@Bean
	PetClient petClient(RestClient.Builder restClientBuilder) {
		return HttpServiceProxyFactory
				.builderFor(RestClientAdapter.create(restClientBuilder.baseUrl(petServiceBaseUrl).build()))
				.build()
				.createClient(PetClient.class);
	}

}
