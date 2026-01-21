package com.abhishekvermaa10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * @author abhishekvermaa10
 */
@Configuration
public class RestClientConfig {
	
	@Bean
	RestClient restClient() {
		return RestClient.builder()
				.build();
	}

}
