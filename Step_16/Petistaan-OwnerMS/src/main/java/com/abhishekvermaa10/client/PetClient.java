package com.abhishekvermaa10.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;

/**
 * @author abhishekvermaa10
 */
@HttpExchange
public interface PetClient {
	
	@PostExchange
	Integer savePet(@RequestBody PetDTO petDTO);
	
	@GetExchange("/{petId}")
	PetDTO findPet(@PathVariable int petId);
	
	@PatchExchange("/{petId}")
	void updatePetDetails(@PathVariable int petId, @RequestBody UpdatePetDTO updatePetDTO);
	
	@DeleteExchange("/{petId}")
	void deletePet(@PathVariable int petId);

}
