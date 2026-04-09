package com.abhishekvermaa10.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.abhishekvermaa10.dto.MailDTO;

/**
 * @author abhishekvermaa10
 */
@HttpExchange
public interface MailClient {
	
	@PostExchange
	String sendEmail(@RequestBody MailDTO mailDTO);

}
