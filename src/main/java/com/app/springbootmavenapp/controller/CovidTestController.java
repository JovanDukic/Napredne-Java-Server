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

import com.app.springbootmavenapp.dto.CovidTestDto;
import com.app.springbootmavenapp.dto.SearchRequest;
import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.service.CovidTestService;
import com.app.springbootmavenapp.util.Utils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/covidTest")
public class CovidTestController {

	private final CovidTestService covidTestService;

	@GetMapping("/checkup/{id}")
	public ResponseEntity<HttpResponse> findCovidTestsByCheckup(@PathVariable("id") Long checkupId) {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Covid tests received!",
				Map.of("values", covidTestService.findAllByCheckupId(checkupId)), HttpStatus.OK));
	}

	@PostMapping("/create")
	public ResponseEntity<HttpResponse> createCovidTest(@RequestBody @Valid CovidTestDto covidTestDto) {
		covidTestService.createCovidTest(covidTestDto);
		return new ResponseEntity<HttpResponse>(
				Utils.generateHttpResponse("Covid test has been successfully created!", HttpStatus.CREATED),
				HttpStatus.CREATED);
	}

	@PostMapping("/search")
	public ResponseEntity<HttpResponse> covidTestSerach(@RequestBody @Valid SearchRequest searchRequest) {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Covid tests received!",
				Map.of("values", covidTestService.covidTestSerach(searchRequest)), HttpStatus.OK));
	}

	@PostMapping("/delete")
	public ResponseEntity<HttpResponse> deleteCovidTest(@RequestBody @Valid CovidTestDto covidTestDto) {
		covidTestService.deleteCovidTest(covidTestDto);
		return ResponseEntity.ok(Utils.generateHttpResponse("Covid test " + covidTestDto.getId() + " has been deleted!", HttpStatus.OK));
	}

}
