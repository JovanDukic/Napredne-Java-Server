package com.app.springbootmavenapp.mappers;

import org.springframework.stereotype.Component;

import com.app.springbootmavenapp.dto.DoctorDto;
import com.app.springbootmavenapp.model.Doctor;

@Component
public class DoctorMapper implements CustomMapper<DoctorDto, Doctor> {

	@Override
	public Doctor toEntity(DoctorDto doctorDto) {
		Doctor doctor = new Doctor();
		
		if(doctorDto == null) {
			return doctor;
		}
		
		doctor.setId(doctorDto.getId());
		doctor.setFirstName(doctor.getFirstName());
		doctor.setLastName(doctor.getLastName());
		doctor.setTitle(doctor.getTitle());
		return doctor;
	}

	@Override
	public DoctorDto toDto(Doctor doctor) {
		DoctorDto doctorDto = new DoctorDto();
		doctorDto.setId(doctor.getId());
		doctorDto.setFirstName(doctor.getFirstName());
		doctorDto.setLastName(doctor.getLastName());
		doctorDto.setTitle(doctor.getTitle());
		return doctorDto;
	}

}
