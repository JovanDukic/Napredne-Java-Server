package com.app.springbootmavenapp.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.springbootmavenapp.dto.LoginRequest;
import com.app.springbootmavenapp.dto.RegisterRequest;
import com.app.springbootmavenapp.exceptions.CustomException;
import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.service.AuthenticationService;
import com.app.springbootmavenapp.util.Utils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<HttpResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
		HttpResponse httpResponse;

		try {
			authenticationService.register(registerRequest);
			httpResponse = Utils.generateHttpResponse("Check your email to activate the account!", HttpStatus.CREATED);
		} catch (CustomException e) {
			httpResponse = Utils.generateExceptionHttpResponse(e.getMessage(), e.getHttpStatus());
		}

		return new ResponseEntity<HttpResponse>(httpResponse, httpResponse.getStatus());
	}

	@GetMapping("/activate/{verificationToken}")
	public ResponseEntity<HttpResponse> activateAccount(@PathVariable String verificationToken) {
		HttpResponse httpResponse;

		try {
			authenticationService.activateAccount(verificationToken);
			httpResponse = Utils.generateHttpResponse("Your account has been successfully activated!", HttpStatus.OK);
		} catch (CustomException e) {
			httpResponse = Utils.generateExceptionHttpResponse(e.getMessage(), e.getHttpStatus());
		}

		return new ResponseEntity<HttpResponse>(httpResponse, httpResponse.getStatus());
	}

	@PostMapping("/login")
	public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
		HttpResponse httpResponse;

		try {
			httpResponse = Utils.generateHttpResponseWithData("You are logged in!",
					Map.of("value", authenticationService.login(loginRequest)), HttpStatus.OK);
		} catch (CustomException e) {
			httpResponse = Utils.generateExceptionHttpResponse(e.getMessage(), e.getHttpStatus());
		}

		return new ResponseEntity<HttpResponse>(httpResponse, httpResponse.getStatus());
	}

}
