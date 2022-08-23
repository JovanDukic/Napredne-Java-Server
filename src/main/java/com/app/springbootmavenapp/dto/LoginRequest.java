package com.app.springbootmavenapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotNull(message = "Username is required!")
	@NotEmpty(message = "Username can not be empty!")
	private String username;

	@NotNull(message = "Password is required!")
	@NotEmpty(message = "Password can not be empty!")
	private String password;

}
