package com.abhishekvermaa10.service.impl;

import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.enums.MailType;
import com.abhishekvermaa10.service.MailService;

import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;
	private final Configuration templateConfig;
	@Value("${spring.mail.username}")
	private String senderEmail;
	@Value("${success.message}")
	private String successMessage;
	@Value("${failure.message}")
	private String failureMessage;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
			mimeMessageHelper.setFrom(senderEmail);
			mimeMessageHelper.setTo(mailDTO.to());
			mimeMessageHelper.setSubject(mailDTO.category().getSubject());
			mimeMessageHelper.setText(buildMailBodyWithTemplate(mailDTO.category(), mailDTO.firstName().concat(" ").concat(mailDTO.lastName())), true);
			mailSender.send(message);
			return String.format(successMessage, mailDTO.to(), LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			return String.format(failureMessage, mailDTO.to(), LocalDateTime.now());
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
