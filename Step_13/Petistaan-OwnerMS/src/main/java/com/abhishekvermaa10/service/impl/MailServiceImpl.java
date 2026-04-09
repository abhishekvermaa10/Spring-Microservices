package com.abhishekvermaa10.service.impl;

import org.springframework.stereotype.Service;

import com.abhishekvermaa10.client.MailClient;
import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.service.MailService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

	private final MailClient mailClient;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		return mailClient.sendEmail(mailDTO);
	}

}
