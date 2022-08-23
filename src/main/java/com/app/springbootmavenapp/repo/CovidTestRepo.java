package com.app.springbootmavenapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.springbootmavenapp.model.CovidTest;

public interface CovidTestRepo extends JpaRepository<CovidTest, Long> {

	Optional<List<CovidTest>> findAllByCheckupId(Long checkupId);
	
}
