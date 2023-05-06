package com.dolga.unidad46.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.dolga.unidad46.dtos.InternoErrorResponse;

@ControllerAdvice
public class ControlAdvice {

	@ExceptionHandler(NuevoInternoException.class)
	public ResponseEntity<InternoErrorResponse> handleCustomException(NuevoInternoException ex, WebRequest request) {
		var response = InternoErrorResponse.builder()
				.state("ERROR")
				.mensaje(ex.getMensaje())
				.build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
