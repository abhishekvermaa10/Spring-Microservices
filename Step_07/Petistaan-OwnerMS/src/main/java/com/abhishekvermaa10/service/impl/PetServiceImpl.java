package com.abhishekvermaa10.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.abhishekvermaa10.dto.DummyPetDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;
import com.abhishekvermaa10.service.PetService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author abhishekvermaa10
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {
	
	private final RestClient loadBalancedRestClient;
	@Value("${pet.service.base.url}")
	private String petServiceBaseUrl;

	@Override
	public Integer savePet(PetDTO petDTO) {
		ResponseEntity<Integer> response = loadBalancedRestClient.post()
				.uri(petServiceBaseUrl)
				.body(petDTO)
				.retrieve()
				.toEntity(Integer.class);
		return response.getBody();
	}

	@CircuitBreaker(name = "petCircuitBreaker", fallbackMethod = "findPetFallback")
	@Retry(name = "petRetry")
	@Override
	public PetDTO findPet(int petId) {
		log.info("===== In findPet =====");
		ResponseEntity<PetDTO> response = loadBalancedRestClient.get()
			.uri(petServiceBaseUrl + "/{petId}", petId)
			.retrieve()
			.toEntity(PetDTO.class);
		return response.getBody();
	}
	
	public PetDTO findPetFallback(int petId, Throwable exception) {
		log.info("===== In findPetFallback =====");
		log.error("Pet service failed for petId {} due to {}", petId, exception.getMessage());
		PetDTO petDTO = new DummyPetDTO();
		petDTO.setId(petId);
		return petDTO;
	}
	
	@Retry(name = "petRetry")
	@Override
	public void updatePetDetails(int petId, String petName) {
		UpdatePetDTO updatePetDTO = new UpdatePetDTO(petName);
		loadBalancedRestClient.patch()
			.uri(petServiceBaseUrl + "/{petId}", petId)
			.body(updatePetDTO)
			.retrieve()
			.toBodilessEntity();
	}

	@Override
	public void deletePet(int petId) {
		loadBalancedRestClient.delete()
			.uri(petServiceBaseUrl + "/{petId}", petId)
			.retrieve()
			.toBodilessEntity();
	}

}
