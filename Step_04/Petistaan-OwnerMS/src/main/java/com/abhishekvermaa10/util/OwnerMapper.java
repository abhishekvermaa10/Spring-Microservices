package com.abhishekvermaa10.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.entity.Owner;

/**
 * @author abhishekvermaa10
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {
	
	@Mapping(source = "petDTO.id", target = "petId")
	Owner ownerDTOToOwner(OwnerDTO ownerDTO);

	@Mapping(target = "petDTO", ignore = true)
	OwnerDTO ownerToOwnerDTO(Owner owner);

}
