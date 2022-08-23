package com.app.springbootmavenapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmbulanceDto implements EntityDto {

	private Long id;
	private String ambulanceName;

}
