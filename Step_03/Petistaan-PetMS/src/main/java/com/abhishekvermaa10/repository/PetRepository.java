package com.abhishekvermaa10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhishekvermaa10.entity.Pet;

/**
 * @author abhishekvermaa10
 */
public interface PetRepository extends JpaRepository<Pet, Integer> {

	@Query("""
			SELECT
			 CASE
			  WHEN TYPE(p) = DomesticPet THEN 'DOMESTIC'
			  WHEN TYPE(p) = WildPet THEN 'WILD'
			 END,
			 p.gender,
			 p.type,
			 COUNT(p)
			FROM Pet p
			GROUP BY
			 CASE
			  WHEN TYPE(p) = DomesticPet THEN 'DOMESTIC'
			  WHEN TYPE(p) = WildPet THEN 'WILD'
			 END,
			 p.gender,
			 p.type
			""")
	List<Object[]> fetchStatistics();

}
