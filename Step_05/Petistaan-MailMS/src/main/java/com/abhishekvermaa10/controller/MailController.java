package com.abhishekvermaa10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.service.MailService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@RequestMapping("/mails")
@RestController
public class MailController {
	
	private final MailService mailService;
	
	@PostMapping
	public ResponseEntity<String> sendEmail(@RequestBody MailDTO mailDTO) {
		String response = mailService.sendEmail(mailDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
