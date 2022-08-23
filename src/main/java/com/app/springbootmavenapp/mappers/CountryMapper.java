package com.app.springbootmavenapp.mappers;

import org.springframework.stereotype.Component;

import com.app.springbootmavenapp.dto.CountryDto;
import com.app.springbootmavenapp.model.Country;

@Component
public class CountryMapper implements CustomMapper<CountryDto, Country> {

	@Override
	public Country toEntity(CountryDto countryDto) {
		Country country = new Country();
		
		if(countryDto == null) {
			return country;
		}
		
		country.setId(countryDto.getId());
		country.setCountryName(countryDto.getCountryName());
		return country;
	}

	@Override
	public CountryDto toDto(Country country) {
		CountryDto countryDto = new CountryDto();
		countryDto.setId(country.getId());
		countryDto.setCountryName(country.getCountryName());
		return countryDto;
	}

}
