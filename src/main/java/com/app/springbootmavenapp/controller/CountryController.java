package com.app.springbootmavenapp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.service.CountryService;
import com.app.springbootmavenapp.util.Utils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {

	private final CountryService countryService;

	@GetMapping("/all")
	public ResponseEntity<HttpResponse> getAllCountries() {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Countries retrieved!",
				Map.of("values", countryService.findAllCountries()), HttpStatus.OK));
	}
	
}
