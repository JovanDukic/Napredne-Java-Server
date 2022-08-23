package com.app.springbootmavenapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootmavenapp.dto.AuthenticationResponse;
import com.app.springbootmavenapp.dto.LoginRequest;
import com.app.springbootmavenapp.dto.RegisterRequest;
import com.app.springbootmavenapp.exceptions.CustomException;
import com.app.springbootmavenapp.mappers.CountryMapper;
import com.app.springbootmavenapp.model.AccountVerificationToken;
import com.app.springbootmavenapp.model.User;
import com.app.springbootmavenapp.model.UserRole;
import com.app.springbootmavenapp.repo.RoleRepo;
import com.app.springbootmavenapp.repo.UserRepo;
import com.app.springbootmavenapp.util.JwtUtil;
import com.app.springbootmavenapp.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final MailService mailService;
	private final AccountVerificationTokenSerivce verificationTokenSerivce;
	private final CountryMapper countryMapper;

	public void register(RegisterRequest registerRequest) {
		User user = new User();

		user.setFirstName(registerRequest.getFirstName());
		user.setLastName(registerRequest.getLastName());

		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());

		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEnabled(false);

		user.setAge(registerRequest.getAge().intValue());
		user.setGender(registerRequest.getGender());
		user.setPhone(registerRequest.getPhone());
		user.setCountry(countryMapper.toEntity(registerRequest.getCountryDto()));

		UserRole userRole = roleRepo.findByRoleName("USER")
				.orElseThrow(() -> new CustomException("USER role does not exist!", HttpStatus.INTERNAL_SERVER_ERROR));

		user.addRole(userRole);

		userRepo.save(user);

		Utils.sendActivationEmail(mailService, generateAccountVerificationToken(user));
	}

	public void activateAccount(String verificationToken) {
		AccountVerificationToken accountVerificationToken = findByVerificationToken(verificationToken);

		if (accountVerificationToken.isActivated()) {
			throw new CustomException("Account has already beed activated!", HttpStatus.BAD_REQUEST);
		} else {
			accountVerificationToken.setActivated(true);
			verificationTokenSerivce.updateAccountVerificationToken(accountVerificationToken);
		}

		activateUser(accountVerificationToken.getUser());
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (Exception e) {
			throw new CustomException("Invalid username/password!", HttpStatus.BAD_REQUEST);
		}

		User user = userRepo.findByUsername(loginRequest.getUsername()).orElse(null);

		return new AuthenticationResponse(jwtUtil.generateToken(loginRequest.getUsername()),
				isAdmin(loginRequest.getUsername()), user.getId());
	}

	private AccountVerificationToken generateAccountVerificationToken(User user) {
		return verificationTokenSerivce.generateAccountVerificationToken(user);
	}

	private AccountVerificationToken findByVerificationToken(String verificationToken) {
		return verificationTokenSerivce.findByVerificationToken(verificationToken);
	}

	private void activateUser(User user) {
		user.setEnabled(true);
		userRepo.save(user);
	}

	private boolean isAdmin(String username) {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new CustomException("User does not exist!", HttpStatus.INTERNAL_SERVER_ERROR));

		for (UserRole userRole : user.getUserRoles()) {
			if (userRole.getRoleName().equals("ADMIN")) {
				return true;
			}
		}

		return false;
	}

}
