package com.app.springbootmavenapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.springbootmavenapp.model.Country;

public interface CountryRepo extends JpaRepository<Country, Long> {

}
