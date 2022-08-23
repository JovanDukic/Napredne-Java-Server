package com.app.springbootmavenapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootmavenapp.model.Country;
import com.app.springbootmavenapp.repo.CountryRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {

	private final CountryRepo countryRepo;

	public List<Country> findAllCountries() {
		return countryRepo.findAll();
	}

}
