package com.abhishekvermaa10.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;
import com.abhishekvermaa10.service.PetService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
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

	@Override
	public PetDTO findPet(int petId) {
		ResponseEntity<PetDTO> response = loadBalancedRestClient.get()
			.uri(petServiceBaseUrl + "/{petId}", petId)
			.retrieve()
			.toEntity(PetDTO.class);
		return response.getBody();
	}

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
