package com.abhishekvermaa10.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.service.MailService;

/**
 * @author abhishekvermaa10
 */
@RequestMapping("/mails")
@RestController
public class MailController {
	
	private final MailService mailService;
	private final MailService mailService2;
	
	public MailController(@Qualifier("mailServiceImpl") MailService mailService,
			@Qualifier("mailServiceImpl2") MailService mailService2) {
		this.mailService = mailService;
		this.mailService2 = mailService2;
	}
	
	@PostMapping
	public ResponseEntity<String> sendEmail(@RequestBody MailDTO mailDTO) {
		String response = mailService.sendEmail(mailDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/basic")
	public ResponseEntity<String> sendBasicEmail(@RequestBody MailDTO mailDTO) {
		String response = mailService2.sendEmail(mailDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
