package com.abhishekvermaa10.service;

import com.abhishekvermaa10.dto.MailDTO;

/**
 * @author abhishekvermaa10
 */
public interface MailService {

	String sendEmail(MailDTO mailDTO);

}
