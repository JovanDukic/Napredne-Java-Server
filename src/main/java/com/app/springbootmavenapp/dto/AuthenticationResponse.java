package com.app.springbootmavenapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	private String authorizationToken;
	private boolean admin;
	private Long userId;

}
