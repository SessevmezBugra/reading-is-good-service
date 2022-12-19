package com.getir.readingisgood.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class IMExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleExceptions(Exception ex, WebRequest request) {
		log.error("ControllerAdvice -> ExceptionHandler -> ", ex, request);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<?> handleAccessDeniedException(Exception ex, WebRequest request) {
		log.error("ControllerAdvice -> ExceptionHandler -> ", ex, request);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Admin role is required. Please login with admin user.");
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
}