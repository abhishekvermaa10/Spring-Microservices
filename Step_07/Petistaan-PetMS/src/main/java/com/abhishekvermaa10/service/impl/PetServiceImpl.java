package com.abhishekvermaa10.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.abhishekvermaa10.dto.PetCategoryStatisticsDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.PetGenderStatisticsDTO;
import com.abhishekvermaa10.dto.PetStatisticsDTO;
import com.abhishekvermaa10.entity.Pet;
import com.abhishekvermaa10.enums.Gender;
import com.abhishekvermaa10.enums.PetType;
import com.abhishekvermaa10.exception.PetNotFoundException;
import com.abhishekvermaa10.repository.PetRepository;
import com.abhishekvermaa10.service.PetService;
import com.abhishekvermaa10.util.PetMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author abhishekvermaa10
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

	private final PetRepository petRepository;
	private final PetMapper petMapper;
	@Value("${pet.not.found}")
	private String petNotFound;
	private final AtomicInteger counter = new AtomicInteger(0);
	
	@Override
	public Integer savePet(PetDTO petDTO) {
		Pet pet = petMapper.petDTOToPet(petDTO);
		petRepository.save(pet);
		return pet.getId();
	}

	@Override
	public PetDTO findPet(int petId) throws PetNotFoundException {
		simulateFindFailure(petId);
		return petRepository.findById(petId)
				.map(petMapper::petToPetDTO)
				.orElseThrow(() -> new PetNotFoundException(String.format(petNotFound, petId)));
	}
	
	private void simulateFindFailure(int petId) {
		if (petId % 2 == 0) {
			log.error("Simulated find failure for petId: {}", petId);
			throw new RuntimeException(String.format("Simulated find failure for petId: %s", petId));
		}
	}

	@Override
	public void updatePetDetails(int petId, String petName) throws PetNotFoundException {
		simulateUpdateFailure(petId);
		Pet pet = petRepository.findById(petId)
				.orElseThrow(() -> new PetNotFoundException(String.format(petNotFound, petId)));
		pet.setName(petName);
		petRepository.save(pet);
	}
	
	private void simulateUpdateFailure(int petId) {
		int attempt = counter.incrementAndGet();
		if (petId % 2 == 0 && attempt < 3) {
			log.error("Simulated update failure for petId: {}", petId);
			throw new RuntimeException(String.format("Simulated update failure for petId: %s", petId));
		}
		counter.set(0);
	}

	@Override
	public void deletePet(int petId) throws PetNotFoundException {
		boolean petExists = petRepository.existsById(petId);
		if (!petExists) {
			throw new PetNotFoundException(String.format(petNotFound, petId));
		} else {
			petRepository.deleteById(petId);
		}
	}

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
