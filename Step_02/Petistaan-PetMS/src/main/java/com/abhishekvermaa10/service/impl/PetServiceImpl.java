package com.abhishekvermaa10.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhishekvermaa10.dto.PetCategoryStatisticsDTO;
import com.abhishekvermaa10.dto.PetGenderStatisticsDTO;
import com.abhishekvermaa10.dto.PetStatisticsDTO;
import com.abhishekvermaa10.enums.Gender;
import com.abhishekvermaa10.enums.PetType;
import com.abhishekvermaa10.repository.PetRepository;
import com.abhishekvermaa10.service.PetService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

	private final PetRepository petRepository;

	@Override
	public PetStatisticsDTO getStatistics() {
		PetStatisticsDTO petStatisticsDTO = new PetStatisticsDTO();
		List<Object[]> rows = petRepository.fetchStatistics();
		for (Object[] row : rows) {
			String category = (String) row[0];
			Gender gender = (Gender) row[1];
			PetType type = (PetType) row[2];
			long count = (Long) row[3];
			petStatisticsDTO.incrementTotal(count);
			PetCategoryStatisticsDTO petCategoryStatisticsDTO = petStatisticsDTO.getOrCreateCategory(category);
			petCategoryStatisticsDTO.incrementTotal(count);
			PetGenderStatisticsDTO petGenderStatisticsDTO = petCategoryStatisticsDTO.getOrCreateGender(gender);
			petGenderStatisticsDTO.incrementTotal(count);
			petGenderStatisticsDTO.mergeOrCreateType(type, count);
		}
		return petStatisticsDTO;
	}

}
