package com.abhishekvermaa10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.PetStatisticsDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;
import com.abhishekvermaa10.exception.PetNotFoundException;
import com.abhishekvermaa10.service.PetService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@RequestMapping("/pets")
@RestController
public class PetController {
	
	private final PetService petService;
	
	@PostMapping
	public ResponseEntity<Integer> savePet(@RequestBody PetDTO petDTO) {
		Integer petId = petService.savePet(petDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(petId);
	}
	
	@GetMapping("/{petId}")
	public ResponseEntity<PetDTO> findPet(@PathVariable int petId) throws PetNotFoundException {
		PetDTO petDTO = petService.findPet(petId);
		return ResponseEntity.status(HttpStatus.OK).body(petDTO);
	}
	
	@PatchMapping("/{petId}")
	public ResponseEntity<Void> updatePetDetails(@PathVariable int petId, @RequestBody UpdatePetDTO updatePetDTO) throws PetNotFoundException {
		petService.updatePetDetails(petId, updatePetDTO.name());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/{petId}")
	public ResponseEntity<Void> deletePet(@PathVariable int petId) throws PetNotFoundException {
		petService.deletePet(petId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/stats")
	public ResponseEntity<PetStatisticsDTO> getStatistics() {
		PetStatisticsDTO petStatisticsDTO = petService.getStatistics();
		return ResponseEntity.status(HttpStatus.OK).body(petStatisticsDTO);
	}

}
