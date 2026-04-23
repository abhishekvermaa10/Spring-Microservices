package com.abhishekvermaa10.event;

import java.time.Instant;

import com.abhishekvermaa10.dto.MailDTO;

/**
 * @author abhishekvermaa10
 */
public record MailEvent(String eventId, MailDTO mailDTO, Instant timestamp) {

}
