package com.app.springbootmavenapp.mappers;

import org.springframework.stereotype.Component;

import com.app.springbootmavenapp.dto.UserDto;
import com.app.springbootmavenapp.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper implements CustomMapper<UserDto, User> {

	private final CountryMapper countryMapper;

	@Override
	public User toEntity(UserDto userDto) {
		User user = new User();

		if (userDto == null) {
			return user;
		}

		user.setId(userDto.getId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setAge(userDto.getAge());
		user.setGender(userDto.getGender());
		user.setEnabled(userDto.isEnabled());
		user.setCountry(countryMapper.toEntity(userDto.getCountryDto()));
		return user;
	}

	@Override
	public UserDto toDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setPhone(user.getPhone());
		userDto.setAge(user.getAge());
		userDto.setGender(user.getGender());
		userDto.setEnabled(user.isEnabled());
		userDto.setCountryDto(countryMapper.toDto(user.getCountry()));
		return userDto;
	}

}
