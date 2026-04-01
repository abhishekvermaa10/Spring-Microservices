package com.abhishekvermaa10.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;

/**
 * @author abhishekvermaa10
 */
@FeignClient(name = "petistaan-petms")
public interface PetClient {
	
	@PostMapping("/pets")
	Integer savePet(@RequestBody PetDTO petDTO);
	
	@GetMapping("/pets/{petId}")
	PetDTO findPet(@PathVariable int petId);
	
	@PatchMapping("/pets/{petId}")
	void updatePetDetails(@PathVariable int petId, @RequestBody UpdatePetDTO updatePetDTO);
	
	@DeleteMapping("/pets/{petId}")
	void deletePet(@PathVariable int petId);

}
