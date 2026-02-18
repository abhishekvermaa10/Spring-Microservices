package com.abhishekvermaa10.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author abhishekvermaa10
 */
@RefreshScope
@ConfigurationProperties(prefix = "spring.mail")
@Component
public class MailConfig {
	
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

}
