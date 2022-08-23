package com.app.springbootmavenapp.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckupDto implements EntityDto {

	private Long id;

	private Timestamp date;

	@NotNull(message = "User is required!")
	private UserDto userDto;

	@NotNull(message = "Doctor is required!")
	private DoctorDto doctorDto;

	@NotNull(message = "Ambulance is required!")
	private AmbulanceDto ambulanceDto;

}
