package com.app.springbootmavenapp.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4869258812508606234L;

	private final HttpStatus httpStatus;

	public CustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
