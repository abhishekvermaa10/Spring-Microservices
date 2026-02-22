package com.abhishekvermaa10.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.abhishekvermaa10.config.MailConfig;
import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.service.MailService;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class MailServiceImpl2 implements MailService {

	private final JavaMailSender mailSender;
	private final MailConfig mailConfig;
	@Value("${success.message}")
	private String successMessage;
	@Value("${failure.message}")
	private String failureMessage;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
			mimeMessageHelper.setFrom(mailConfig.getUsername());
			mimeMessageHelper.setTo(mailDTO.to());
			mimeMessageHelper.setSubject(mailDTO.category().getSubject());
			mimeMessageHelper.setText(buildMailBodyWithTemplate(
					mailDTO.firstName().concat(" ").concat(mailDTO.lastName())), true);
			mailSender.send(message);
			return String.format(successMessage, mailDTO.to(), LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(failureMessage, mailDTO.to(), LocalDateTime.now());
		}
	}

	private String buildMailBodyWithTemplate(String ownerName) {
		return ownerName;
	}

}
