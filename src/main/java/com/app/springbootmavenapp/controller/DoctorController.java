package com.app.springbootmavenapp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.service.DoctorService;
import com.app.springbootmavenapp.util.Utils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

	private final DoctorService doctorService;

	@GetMapping("/all")
	public ResponseEntity<HttpResponse> getAllDoctors() {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Doctors retrieved!",
				Map.of("values", doctorService.findAllDoctors()), HttpStatus.OK));
	}

}
