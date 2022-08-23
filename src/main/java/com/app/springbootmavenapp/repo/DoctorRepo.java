package com.app.springbootmavenapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.springbootmavenapp.model.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
