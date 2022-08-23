package com.app.springbootmavenapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.springbootmavenapp.model.AccountVerificationToken;

public interface AccountVerificationTokenRepo extends JpaRepository<AccountVerificationToken, Long> {

	Optional<AccountVerificationToken> findByVerificationToken(String token);

}
