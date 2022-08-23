package com.app.springbootmavenapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

	@NotNull(message = "Record id is required!")
	private Long recordId;
	
	@NotNull(message = "Search type is required!")
	@NotEmpty(message = "Search type can not be empty!")
	private String searchType;
	
	@NotNull(message = "Pattern is required!")
	private String pattern;
	
}
