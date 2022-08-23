package com.app.springbootmavenapp.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.springbootmavenapp.exceptions.CustomException;
import com.app.springbootmavenapp.model.AccountVerificationToken;
import com.app.springbootmavenapp.model.User;
import com.app.springbootmavenapp.repo.AccountVerificationTokenRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountVerificationTokenSerivce {

	private final AccountVerificationTokenRepo verificationTokenRepo;

	public AccountVerificationToken generateAccountVerificationToken(User user) {
		String verificationToken = UUID.randomUUID().toString();
		AccountVerificationToken accountVerificationToken = AccountVerificationToken.builder()
				.verificationToken(verificationToken).user(user).build();

		verificationTokenRepo.save(accountVerificationToken);

		return accountVerificationToken;
	}

	public AccountVerificationToken findByVerificationToken(String verificationToken) {
		return verificationTokenRepo.findByVerificationToken(verificationToken)
				.orElseThrow(() -> new CustomException("Token wasn't found!", HttpStatus.INTERNAL_SERVER_ERROR));
	}

	public void updateAccountVerificationToken(AccountVerificationToken verificationToken) {
		verificationTokenRepo.save(verificationToken);
	}

}
