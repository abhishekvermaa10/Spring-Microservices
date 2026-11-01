package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.Gender;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Setter
@Getter
public class OwnerDTO {

	@EqualsAndHashCode.Include
	private Integer id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private String city;
	private String state;
	@EqualsAndHashCode.Include
	private String mobileNumber;
	@EqualsAndHashCode.Include
	private String emailId;
	private PetDTO petDTO;

}
