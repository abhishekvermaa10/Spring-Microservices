package com.abhishekvermaa10.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@ToString(callSuper = true)
@Setter
@Getter
public class DomesticPetDTO extends PetDTO {

	private LocalDate birthDate;

}
