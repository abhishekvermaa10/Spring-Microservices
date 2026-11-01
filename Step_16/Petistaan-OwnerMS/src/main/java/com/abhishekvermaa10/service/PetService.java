package com.abhishekvermaa10.service;

import com.abhishekvermaa10.dto.PetDTO;

/**
 * @author abhishekvermaa10
 */
public interface PetService {
	
	Integer savePet(PetDTO petDTO);
	
	PetDTO findPet(int petId);
	
	void updatePetDetails(int petId, String petName);
	
	void deletePet(int petId);

}
