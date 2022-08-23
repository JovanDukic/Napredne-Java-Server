package com.app.springbootmavenapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootmavenapp.dto.SearchRequest;
import com.app.springbootmavenapp.dto.UserDto;
import com.app.springbootmavenapp.exceptions.CustomException;
import com.app.springbootmavenapp.mappers.UserMapper;
import com.app.springbootmavenapp.model.User;
import com.app.springbootmavenapp.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepo userRepo;
	private final UserMapper userMapper;

	public User updateUser(UserDto userDto) {
		return userRepo.save(userMapper.toEntity(userDto));
	}

	public List<UserDto> findAllUsers() {
		List<User> users = userRepo.findAll();

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == 1) {
				users.remove(i);
				break;
			}
		}

		return users.stream().map((user) -> userMapper.toDto(user)).collect(Collectors.toList());
	}

	public UserDto findUserById(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(
				() -> new CustomException("User with id " + userId + " does not exist!", HttpStatus.BAD_REQUEST));
		return userMapper.toDto(user);
	}

	public List<UserDto> userSearch(@Valid SearchRequest searchRequest) {
		List<UserDto> users = findAllUsers();

		List<UserDto> finalList = new LinkedList<>();

		String pattern = searchRequest.getPattern();

		for (UserDto userDto : users) {
			switch (searchRequest.getSearchType()) {
			case "USERNAME": {
				if (checkCondition(userDto.getUsername(), pattern)) {
					finalList.add(userDto);
				}
			}
			case "FIRSTNAME": {
				if (checkCondition(userDto.getFirstName(), pattern)) {
					finalList.add(userDto);
				}
				break;
			}
			case "LASTNAME": {
				if (checkCondition(userDto.getLastName(), pattern)) {
					finalList.add(userDto);
				}
				break;
			}
			case "AGE": {
				if (checkCondition(String.valueOf(userDto.getAge()), pattern)) {
					finalList.add(userDto);
				}
				break;
			}
			case "GENDER": {
				if (checkCondition(userDto.getGender(), pattern)) {
					finalList.add(userDto);
				}
				break;
			}
			case "COUNTRY": {
				if (checkCondition(userDto.getCountryDto().getCountryName(), pattern)) {
					finalList.add(userDto);
				}
				break;
			}
			}
		}

		return finalList;
	}

	private boolean checkCondition(String origin, String pattern) {
		return origin.contains(pattern);
	}

	public void deleteUser(UserDto userDto) {
		userRepo.delete(userMapper.toEntity(userDto));
	}

}
