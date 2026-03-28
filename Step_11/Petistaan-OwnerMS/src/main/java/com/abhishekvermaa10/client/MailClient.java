package com.abhishekvermaa10.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.abhishekvermaa10.dto.MailDTO;

/**
 * @author abhishekvermaa10
 */
@FeignClient(name = "petistaan-mailms")
public interface MailClient {
	
	@PostMapping("/mails")
	String sendEmail(@RequestBody MailDTO mailDTO);

}
