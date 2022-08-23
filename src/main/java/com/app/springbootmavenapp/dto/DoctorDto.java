package com.app.springbootmavenapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto implements EntityDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String title;

}
