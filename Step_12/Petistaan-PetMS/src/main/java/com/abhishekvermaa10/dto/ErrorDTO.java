package com.abhishekvermaa10.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

/**
 * @author abhishekvermaa10
 */
public record ErrorDTO(String message, HttpStatus error, Integer status, LocalDateTime timestamp) {

}
