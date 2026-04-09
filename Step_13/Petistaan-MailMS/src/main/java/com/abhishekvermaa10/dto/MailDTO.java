package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.MailType;

/**
 * @author abhishekvermaa10
 */
public record MailDTO(String to, String firstName, String lastName, MailType category) {

}
