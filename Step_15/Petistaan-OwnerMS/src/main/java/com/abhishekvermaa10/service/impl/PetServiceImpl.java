package com.abhishekvermaa10.service.impl;

import org.springframework.stereotype.Service;

import com.abhishekvermaa10.client.PetClient;
import com.abhishekvermaa10.dto.DummyPetDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;
import com.abhishekvermaa10.service.PetService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {
	
	private final PetClient petClient;

	@Override
	public Integer savePet(PetDTO petDTO) {
		return petClient.savePet(petDTO);
	}

	@CircuitBreaker(name = "petCircuitBreaker", fallbackMethod = "findPetFallback")
	@Retry(name = "petRetry")
	@Override
	public PetDTO findPet(int petId) {
		return petClient.findPet(petId);
	}
	
	public PetDTO findPetFallback(int petId, Throwable exception) {
		PetDTO petDTO = new DummyPetDTO();
		petDTO.setId(petId);
		return petDTO;
	}
	
	@Retry(name = "petRetry")
	@Override
	public void updatePetDetails(int petId, String petName) {
		UpdatePetDTO updatePetDTO = new UpdatePetDTO(petName);
		petClient.updatePetDetails(petId, updatePetDTO);
	}

	@Override
	public void deletePet(int petId) {
		petClient.deletePet(petId);
	}

}
