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

import com.app.springbootmavenapp.dto.CheckupDto;
import com.app.springbootmavenapp.dto.SearchRequest;
import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.service.CheckupService;
import com.app.springbootmavenapp.util.Utils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkup")
public class CheckupController {

	private final CheckupService checkupService;

	@GetMapping("/user/{id}")
	public ResponseEntity<HttpResponse> findCheckupsByUser(@PathVariable("id") Long userId) {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Checkups received!",
				Map.of("values", checkupService.findAllByUserId(userId)), HttpStatus.OK));
	}

	@PostMapping("/create")
	public ResponseEntity<HttpResponse> createCheckup(@RequestBody @Valid CheckupDto checkupDto) {
		checkupService.createCheckup(checkupDto);
		return new ResponseEntity<HttpResponse>(
				Utils.generateHttpResponse("Checkup has been created!", HttpStatus.CREATED), HttpStatus.CREATED);
	}

	@PostMapping("/search")
	public ResponseEntity<HttpResponse> checkupSearch(@RequestBody @Valid SearchRequest searchRequest) {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Checkups received!",
				Map.of("values", checkupService.checkupSearch(searchRequest)), HttpStatus.OK));
	}

	@PostMapping("/delete")
	public ResponseEntity<HttpResponse> deleteCheckup(@RequestBody @Valid CheckupDto checkupDto) {
		checkupService.deleteCheckup(checkupDto);
		return ResponseEntity
				.ok(Utils.generateHttpResponse("Checkup " + checkupDto.getId() + " has been deleted!", HttpStatus.OK));
	}

}
