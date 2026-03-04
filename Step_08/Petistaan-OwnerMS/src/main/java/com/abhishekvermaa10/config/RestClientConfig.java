package com.abhishekvermaa10.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

/**
 * @author abhishekvermaa10
 */
@Configuration
public class RestClientConfig {
	
	@Primary
	@Bean
	RestClient.Builder restClientBuilder() {
		return RestClient.builder();
	}
	
	@Bean
	RestClient loadBalancedRestClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
		return builder.build();
	}
	
	@LoadBalanced
	@Bean
	RestClient.Builder loadBalancedRestClientBuilder() {
		return RestClient.builder();
	}

}
