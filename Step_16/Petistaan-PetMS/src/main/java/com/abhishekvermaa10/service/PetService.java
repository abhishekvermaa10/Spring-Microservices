package com.abhishekvermaa10.service;

import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.PetStatisticsDTO;
import com.abhishekvermaa10.exception.PetNotFoundException;

/**
 * @author abhishekvermaa10
 */
public interface PetService {
	
	Integer savePet(PetDTO petDTO);
	
	PetDTO findPet(int petId) throws PetNotFoundException;
	
	void updatePetDetails(int petId, String petName) throws PetNotFoundException;
	
	void deletePet(int petId) throws PetNotFoundException;

	PetStatisticsDTO getStatistics();

}
