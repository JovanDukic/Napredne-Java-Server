package com.app.springbootmavenapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.springbootmavenapp.model.Ambulance;

public interface AmbulanceRepo extends JpaRepository<Ambulance, Long> {

}
