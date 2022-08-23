package com.app.springbootmavenapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements EntityDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private int age;
	private String gender;
	private String phone;
	private boolean enabled;
	private CountryDto countryDto;

}
