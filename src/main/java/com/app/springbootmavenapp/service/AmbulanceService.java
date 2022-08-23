package com.app.springbootmavenapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootmavenapp.model.Ambulance;
import com.app.springbootmavenapp.repo.AmbulanceRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AmbulanceService {

	private final AmbulanceRepo ambulanceRepo;

	public List<Ambulance> findAllAlmbulances() {
		return ambulanceRepo.findAll();
	}
}
