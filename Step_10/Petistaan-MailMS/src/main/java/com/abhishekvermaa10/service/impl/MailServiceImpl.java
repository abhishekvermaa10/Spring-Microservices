package com.abhishekvermaa10.service.impl;

import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.abhishekvermaa10.config.MailConfig;
import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.enums.MailType;
import com.abhishekvermaa10.service.MailService;

import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author abhishekvermaa10
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;
	private final Configuration templateConfig;
	private final MailConfig mailConfig;
	private final Registration registration;
	@Value("${success.message}")
	private String successMessage;
	@Value("${failure.message}")
	private String failureMessage;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		log.info("Received request to send email to: {}", mailDTO.to());
		String serverId = registration.getInstanceId();
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
			mimeMessageHelper.setFrom(mailConfig.getUsername());
			mimeMessageHelper.setTo(mailDTO.to());
			mimeMessageHelper.setSubject(mailDTO.category().getSubject());
			mimeMessageHelper.setText(buildMailBodyWithTemplate(mailDTO.category(), mailDTO.firstName().concat(" ").concat(mailDTO.lastName())), true);
			mailSender.send(message);
			return String.format(successMessage, mailDTO.to(), LocalDateTime.now(), serverId);
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(failureMessage, mailDTO.to(), LocalDateTime.now(), serverId);
		}
	}

	private String buildMailBodyWithTemplate(MailType category, String ownerName) {
		Map<String, String> dataModel = new HashMap<>();
		dataModel.put("ownerName", ownerName);
		Writer writer = new StringWriter();
		try {
			templateConfig.getTemplate(category.getTemplateFileName()).process(dataModel, writer);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return ownerName;
		}
	}

}
