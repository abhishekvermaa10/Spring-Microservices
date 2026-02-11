package com.abhishekvermaa10.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
	
	@Bean
	RestClient loadBalancedRestClient(RestClient.Builder builder) {
		return builder.build();
	}
	
	@LoadBalanced
	@Bean
	RestClient.Builder loadBalancedRestClientBuilder() {
		return RestClient.builder();
	}

}
