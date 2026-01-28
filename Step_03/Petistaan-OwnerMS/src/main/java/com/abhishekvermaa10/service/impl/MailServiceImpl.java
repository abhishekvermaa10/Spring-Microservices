package com.abhishekvermaa10.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.service.MailService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

	private final RestClient restClient;
	@Value("${mail.service.base.url}")
	private String mailServiceBaseUrl;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		ResponseEntity<String> response = restClient.post()
				.uri(mailServiceBaseUrl)
				.body(mailDTO)
				.retrieve()
				.toEntity(String.class);
		return response.getBody();
	}

}
