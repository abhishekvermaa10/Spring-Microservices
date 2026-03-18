package com.abhishekvermaa10.service.impl;

import static com.abhishekvermaa10.enums.MailType.EXIT;
import static com.abhishekvermaa10.enums.MailType.MODIFY;
import static com.abhishekvermaa10.enums.MailType.WELCOME;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abhishekvermaa10.dto.MailDTO;
import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.entity.Owner;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.repository.OwnerRepository;
import com.abhishekvermaa10.service.MailService;
import com.abhishekvermaa10.service.OwnerService;
import com.abhishekvermaa10.service.PetService;
import com.abhishekvermaa10.util.OwnerMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author abhishekvermaa10
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
	
	private final OwnerRepository ownerRepository;
	private final OwnerMapper ownerMapper;
	private final MailService mailService;
	private final PetService petService;
	@Value("${owner.not.found}")
	private String ownerNotFound;
	
	@Override
	public Integer saveOwner(OwnerDTO ownerDTO) {
		Integer petId = petService.savePet(ownerDTO.getPetDTO());
		ownerDTO.getPetDTO().setId(petId);
		Owner owner = ownerMapper.ownerDTOToOwner(ownerDTO);
		ownerRepository.save(owner);
		MailDTO mailDTO = new MailDTO(ownerDTO.getEmailId(), ownerDTO.getFirstName(), ownerDTO.getLastName(), WELCOME);
		log.info(mailService.sendEmail(mailDTO));
		return owner.getId();
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		return ownerRepository.findById(ownerId)
				.map(o -> {
					PetDTO petDTO = petService.findPet(o.getPetId());
					OwnerDTO ownerDTO = ownerMapper.ownerToOwnerDTO(o);
					ownerDTO.setPetDTO(petDTO);
					return ownerDTO;
				})
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
	}
	
	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
		log.info("Updating pet details for ownerId: {}", ownerId);
		Owner owner = ownerRepository.findById(ownerId)
				.map(o -> {
					petService.updatePetDetails(o.getPetId(), petName);
					return o;
				})
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
		MailDTO mailDTO = new MailDTO(owner.getEmailId(), owner.getFirstName(), owner.getLastName(), MODIFY);
		log.info(mailService.sendEmail(mailDTO));
	}
	
	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.map(o -> {
					petService.deletePet(o.getPetId());
					return o;
				})
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
		ownerRepository.deleteById(ownerId);
		MailDTO mailDTO = new MailDTO(owner.getEmailId(), owner.getFirstName(), owner.getLastName(), EXIT);
		log.info(mailService.sendEmail(mailDTO));
	}
	
	@Override
	public Page<OwnerDTO> findAllOwners(Pageable pageable) {
		return ownerRepository.findAll(pageable)
				.map(o -> {
					PetDTO petDTO = petService.findPet(o.getPetId());
					OwnerDTO ownerDTO = ownerMapper.ownerToOwnerDTO(o);
					ownerDTO.setPetDTO(petDTO);
					return ownerDTO;
				});
	}

}
