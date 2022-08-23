package com.app.springbootmavenapp.mappers;

import org.springframework.stereotype.Component;

import com.app.springbootmavenapp.dto.AmbulanceDto;
import com.app.springbootmavenapp.model.Ambulance;

@Component
public class AmbulanceMapper implements CustomMapper<AmbulanceDto, Ambulance> {

	@Override
	public Ambulance toEntity(AmbulanceDto ambulanceDto) {
		Ambulance ambulance = new Ambulance();

		if (ambulanceDto == null) {
			return ambulance;
		}

		ambulance.setId(ambulanceDto.getId());
		ambulance.setAmbulanceName(ambulanceDto.getAmbulanceName());
		return ambulance;
	}

	@Override
	public AmbulanceDto toDto(Ambulance ambulance) {
		AmbulanceDto ambulanceDto = new AmbulanceDto();
		ambulanceDto.setId(ambulance.getId());
		ambulanceDto.setAmbulanceName(ambulance.getAmbulanceName());
		return ambulanceDto;
	}

}
