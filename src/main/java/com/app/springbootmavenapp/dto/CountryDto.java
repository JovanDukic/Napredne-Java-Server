package com.app.springbootmavenapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto implements EntityDto {

	private Long id;
	private String countryName;

}
