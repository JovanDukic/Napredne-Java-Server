package com.app.springbootmavenapp.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.app.springbootmavenapp.model.CovidTest.Result;
import com.app.springbootmavenapp.model.CovidTest.TestType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidTestDto implements EntityDto {

	private Long id;

	@NotNull(message = "Test type is required!")
	private TestType testType;

	private Result result;

	@NotNull(message = "Checkup is required!")
	private CheckupDto checkupDto;
	
	private Timestamp date;

}
