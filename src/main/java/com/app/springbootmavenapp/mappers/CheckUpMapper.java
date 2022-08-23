package com.app.springbootmavenapp.mappers;

import org.springframework.stereotype.Component;

import com.app.springbootmavenapp.dto.CheckupDto;
import com.app.springbootmavenapp.model.Checkup;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckUpMapper implements CustomMapper<CheckupDto, Checkup> {

	private final UserMapper userMapper;
	private final AmbulanceMapper ambulanceMapper;
	private final DoctorMapper doctorMapper;

	@Override
	public Checkup toEntity(CheckupDto checkupDto) {
		Checkup checkup = new Checkup();
		
		if(checkupDto == null) {
			return checkup;
		}
		
		checkup.setId(checkupDto.getId());
		checkup.setDate(checkupDto.getDate());
		checkup.setAmbulance(ambulanceMapper.toEntity(checkupDto.getAmbulanceDto()));
		checkup.setDoctor(doctorMapper.toEntity(checkupDto.getDoctorDto()));
		checkup.setUser(userMapper.toEntity(checkupDto.getUserDto()));
		return checkup;
	}

	@Override
	public CheckupDto toDto(Checkup checkup) {
		CheckupDto checkupDto = new CheckupDto();
		checkupDto.setId(checkup.getId());
		checkupDto.setDate(checkup.getDate());
		checkupDto.setAmbulanceDto(ambulanceMapper.toDto(checkup.getAmbulance()));
		checkupDto.setDoctorDto(doctorMapper.toDto(checkup.getDoctor()));
		checkupDto.setUserDto(userMapper.toDto(checkup.getUser()));
		return checkupDto;
	}

}
