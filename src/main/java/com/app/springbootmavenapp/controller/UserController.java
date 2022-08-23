package com.app.springbootmavenapp.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.springbootmavenapp.dto.SearchRequest;
import com.app.springbootmavenapp.dto.UserDto;
import com.app.springbootmavenapp.exceptions.CustomException;
import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.service.UserService;
import com.app.springbootmavenapp.util.Utils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/all")
	public ResponseEntity<HttpResponse> getAllUsers() {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Users retrieved!",
				Map.of("values", userService.findAllUsers()), HttpStatus.OK));
	}

	@PostMapping("/update")
	public ResponseEntity<HttpResponse> updateUser(@RequestBody @Valid UserDto userDto) {
		HttpResponse httpResponse;

		try {
			userService.updateUser(userDto);
			httpResponse = Utils.generateHttpResponse("Your data has been successfully updated!", HttpStatus.OK);
		} catch (CustomException e) {
			httpResponse = Utils.generateExceptionHttpResponse(e.getMessage(), e.getHttpStatus());
		}

		return new ResponseEntity<HttpResponse>(httpResponse, httpResponse.getStatus());
	}

	@GetMapping("/{id}")
	public ResponseEntity<HttpResponse> getAllUsers(@PathVariable("id") Long userId) {
		HttpResponse httpResponse;

		try {
			httpResponse = Utils.generateHttpResponseWithData("User data retrieved!",
					Map.of("value", userService.findUserById(userId)), HttpStatus.OK);
		} catch (CustomException e) {
			httpResponse = Utils.generateExceptionHttpResponse(e.getMessage(), e.getHttpStatus());
		}

		return new ResponseEntity<HttpResponse>(httpResponse, httpResponse.getStatus());
	}

	@PostMapping("/search")
	public ResponseEntity<HttpResponse> userSearch(@RequestBody @Valid SearchRequest searchRequest) {
		return ResponseEntity.ok(Utils.generateHttpResponseWithData("Users received!",
				Map.of("values", userService.userSearch(searchRequest)), HttpStatus.OK));
	}

	@PostMapping("/delete")
	public ResponseEntity<HttpResponse> deleteUser(@RequestBody @Valid UserDto userDto) {
		userService.deleteUser(userDto);
		return ResponseEntity
				.ok(Utils.generateHttpResponse("User " + userDto.getUsername() + " has been deleted!", HttpStatus.OK));
	}
}
