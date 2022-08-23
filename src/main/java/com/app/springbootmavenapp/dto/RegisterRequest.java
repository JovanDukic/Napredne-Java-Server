package com.app.springbootmavenapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	@NotNull(message = "First name is required!")
	@NotEmpty(message = "First name can not be empty!")
	private String firstName;

	@NotNull(message = "Last name is required!")
	@NotEmpty(message = "Last name can not be empty!")
	private String lastName;

	@NotNull(message = "Username is required!")
	@NotEmpty(message = "Username can not be empty!")
	private String username;

	@NotNull(message = "Password is required!")
	@NotEmpty(message = "Password can not be empty!")
	private String password;

	@Email(message = "Email address has bad format!")
	@NotNull(message = "Email is required!")
	@NotEmpty(message = "Email can not be empty!")
	private String email;

	@NotNull(message = "Age is required!")
	private Integer age;

	@NotNull(message = "Gender is required!")
	@NotEmpty(message = "Gender can not be empty!")
	private String gender;

	@NotNull(message = "Phone number is required!")
	@NotEmpty(message = "Phone number can not be empty!")
	private String phone;

	@NotNull(message = "Country is required!")
	private CountryDto countryDto;

}
