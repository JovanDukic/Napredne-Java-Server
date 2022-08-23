package com.app.springbootmavenapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.springbootmavenapp.model.Checkup;

public interface CheckupRepo extends JpaRepository<Checkup, Long> {

	Optional<List<Checkup>> findAllByUserId(Long userId);

}
