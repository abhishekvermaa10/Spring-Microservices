package com.abhishekvermaa10.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@ToString(callSuper = true)
@Setter
@Getter
public class WildPetDTO extends PetDTO {

	private String birthPlace;

}
