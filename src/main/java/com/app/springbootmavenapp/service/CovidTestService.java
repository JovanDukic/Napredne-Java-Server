package com.app.springbootmavenapp.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootmavenapp.dto.CovidTestDto;
import com.app.springbootmavenapp.dto.SearchRequest;
import com.app.springbootmavenapp.mappers.CovidTestMapper;
import com.app.springbootmavenapp.model.CovidTest;
import com.app.springbootmavenapp.model.CovidTest.Result;
import com.app.springbootmavenapp.repo.CovidTestRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CovidTestService {

	private static final Random RANDOM = new Random();

	private final CovidTestRepo covidTestRepo;
	private final CovidTestMapper covidTestMapper;

	public CovidTest createCovidTest(CovidTestDto covidTestDto) {
		covidTestDto.setResult(RANDOM.nextInt() % 2 == 0 ? Result.POSITIVE : Result.NEGATIVE);
		covidTestDto.setDate(Timestamp.valueOf(LocalDateTime.now()));
		return covidTestRepo.save(covidTestMapper.toEntity(covidTestDto));
	}

	public List<CovidTestDto> findAllByCheckupId(Long checkupId) {
		List<CovidTest> covidTests = covidTestRepo.findAllByCheckupId(checkupId).orElse(new LinkedList<CovidTest>());
		return covidTests.stream().map((covidTest) -> covidTestMapper.toDto(covidTest)).collect(Collectors.toList());
	}

	public List<CovidTestDto> covidTestSerach(@Valid SearchRequest searchRequest) {
		List<CovidTest> covidTests = covidTestRepo.findAllByCheckupId(searchRequest.getRecordId())
				.orElse(new LinkedList<CovidTest>());

		List<CovidTest> filteredList = new LinkedList<>();

		if (searchRequest.getSearchType().equals("Result")) {
			for (CovidTest covidTest : covidTests) {
				if (covidTest.getResult().toString().contains(searchRequest.getPattern())) {
					filteredList.add(covidTest);
				}
			}
		} else if (searchRequest.getSearchType().equals("Test type")) {
			for (CovidTest covidTest : covidTests) {
				if (covidTest.getTestType().toString().contains(searchRequest.getPattern())) {
					filteredList.add(covidTest);
				}
			}
		}

		return filteredList.stream().map((covidTest) -> covidTestMapper.toDto(covidTest)).collect(Collectors.toList());
	}
	
	public void deleteCovidTest(CovidTestDto covidTestDto) {
		covidTestRepo.delete(covidTestMapper.toEntity(covidTestDto));
	}

}
