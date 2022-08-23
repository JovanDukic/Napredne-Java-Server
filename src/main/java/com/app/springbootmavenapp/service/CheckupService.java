package com.app.springbootmavenapp.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springbootmavenapp.dto.CheckupDto;
import com.app.springbootmavenapp.dto.SearchRequest;
import com.app.springbootmavenapp.mappers.CheckUpMapper;
import com.app.springbootmavenapp.model.Checkup;
import com.app.springbootmavenapp.repo.CheckupRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckupService {

	private final CheckupRepo checkupRepo;
	private final CheckUpMapper checkUpMapper;

	public Checkup createCheckup(CheckupDto checkupDto) {
		checkupDto.setDate(Timestamp.valueOf(LocalDateTime.now()));
		return checkupRepo.save(checkUpMapper.toEntity(checkupDto));
	}

	public List<CheckupDto> findAllByUserId(Long userId) {
		List<Checkup> checkups = checkupRepo.findAllByUserId(userId).orElse(new LinkedList<Checkup>());
		return checkups.stream().map((checkup) -> checkUpMapper.toDto(checkup)).collect(Collectors.toList());
	}

	public List<CheckupDto> checkupSearch(SearchRequest searchRequest) {
		List<Checkup> checkups = checkupRepo.findAllByUserId(searchRequest.getRecordId())
				.orElse(new LinkedList<Checkup>());

		List<Checkup> filteredList = new LinkedList<>();

		if (searchRequest.getSearchType().equals("Doctor")) {
			for (Checkup checkup : checkups) {
				if (checkup.getDoctor().getFirstName().contains(searchRequest.getPattern())) {
					filteredList.add(checkup);
				} else if (checkup.getDoctor().getLastName().contains(searchRequest.getPattern())) {
					filteredList.add(checkup);
				}
			}
		} else if (searchRequest.getSearchType().equals("Ambulance")) {
			for (Checkup checkup : checkups) {
				if (checkup.getAmbulance().getAmbulanceName().contains(searchRequest.getPattern())) {
					filteredList.add(checkup);
				}
			}
		}

		return filteredList.stream().map((checkup) -> checkUpMapper.toDto(checkup)).collect(Collectors.toList());
	}

	public void deleteCheckup(CheckupDto checkupDto) {
		checkupRepo.delete(checkUpMapper.toEntity(checkupDto));
	}

}
