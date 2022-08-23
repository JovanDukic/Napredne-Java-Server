package com.app.springbootmavenapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootmavenapp.model.Doctor;
import com.app.springbootmavenapp.repo.DoctorRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {

	private final DoctorRepo doctorRepo;

	public List<Doctor> findAllDoctors() {
		return doctorRepo.findAll();
	}

}
