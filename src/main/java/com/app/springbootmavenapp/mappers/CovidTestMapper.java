package com.app.springbootmavenapp.mappers;

import org.springframework.stereotype.Component;

import com.app.springbootmavenapp.dto.CovidTestDto;
import com.app.springbootmavenapp.model.CovidTest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CovidTestMapper implements CustomMapper<CovidTestDto, CovidTest> {

	private final CheckUpMapper checkUpMapper;

	@Override
	public CovidTest toEntity(CovidTestDto covidTestDto) {
		CovidTest covidTest = new CovidTest();

		if (covidTestDto == null) {
			return covidTest;
		}

		covidTest.setId(covidTestDto.getId());
		covidTest.setTestType(covidTestDto.getTestType());
		covidTest.setResult(covidTestDto.getResult());
		covidTest.setDate(covidTestDto.getDate());
		covidTest.setCheckup(checkUpMapper.toEntity(covidTestDto.getCheckupDto()));

		return covidTest;
	}

	@Override
	public CovidTestDto toDto(CovidTest covidTest) {
		CovidTestDto covidTestDto = new CovidTestDto();
		covidTestDto.setId(covidTest.getId());
		covidTestDto.setTestType(covidTest.getTestType());
		covidTestDto.setResult(covidTest.getResult());
		covidTestDto.setDate(covidTest.getDate());
		covidTestDto.setCheckupDto(checkUpMapper.toDto(covidTest.getCheckup()));
		return covidTestDto;
	}

}
