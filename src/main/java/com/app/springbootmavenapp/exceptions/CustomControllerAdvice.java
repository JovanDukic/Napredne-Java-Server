package com.app.springbootmavenapp.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.util.Utils;

@ControllerAdvice
public class CustomControllerAdvice {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<HttpResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		List<String> violations = new ArrayList<>();
		violations.addAll(constraintViolations.stream().map(viol -> viol.getMessage()).collect(Collectors.toList()));
		return new ResponseEntity<HttpResponse>(Utils.generateExceptionHttpResponseWithData(ex.getMessage(),
				Map.of("values", violations), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<HttpResponse> handleSQLIntegrityConstraintViolation(
			SQLIntegrityConstraintViolationException ex) {
		return new ResponseEntity<HttpResponse>(
				Utils.generateExceptionHttpResponse(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HttpResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<ObjectError> allErrors = ex.getAllErrors();
		List<String> collect = allErrors.stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<HttpResponse>(Utils.generateExceptionHttpResponseWithData(ex.getMessage(),
				Map.of("values", collect), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<HttpResponse> authExceptionHandler(AuthenticationException ex) {
		return new ResponseEntity<HttpResponse>(
				Utils.generateExceptionHttpResponse(ex.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<HttpResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		return new ResponseEntity<HttpResponse>(
				Utils.generateExceptionHttpResponse(e.getRootCause().getMessage(), HttpStatus.BAD_REQUEST),
				HttpStatus.BAD_REQUEST);
	}

}
