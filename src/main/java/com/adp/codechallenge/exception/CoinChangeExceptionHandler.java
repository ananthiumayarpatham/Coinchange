package com.adp.codechallenge.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.adp.codechallenge.dto.CoinChangeError;

@RestControllerAdvice
public class CoinChangeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ CoinChangeException.class, ConstraintViolationException.class })
	public ResponseEntity<CoinChangeError> publishError(Exception ex) {
		CoinChangeError coinChangeError = new CoinChangeError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(coinChangeError, HttpStatus.BAD_REQUEST);
	}
}
