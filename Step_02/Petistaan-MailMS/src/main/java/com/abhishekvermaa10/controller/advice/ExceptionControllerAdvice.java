package com.abhishekvermaa10.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abhishekvermaa10.dto.ErrorDTO;

/**
 * @author abhishekvermaa10
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException exception) {
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED,
				HttpStatus.METHOD_NOT_ALLOWED.value(), LocalDateTime.now());
		return ResponseEntity.status(errorDTO.status()).body(errorDTO);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleGenericException(Exception exception) {
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
		return ResponseEntity.status(errorDTO.status()).body(errorDTO);
	}

}
