package com.app.springbootmavenapp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.service.AmbulanceService;
import com.app.springbootmavenapp.util.Utils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ambulance")
public class AmbulanceController {

	private final AmbulanceService ambulanceService;

	@GetMapping("/all")
	public ResponseEntity<HttpResponse> getAllAmbulances() {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Ambulances retrieved!",
				Map.of("values", ambulanceService.findAllAlmbulances()), HttpStatus.OK));
	}
}
