package com.abhishekvermaa10.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@ToString
@Getter
public class PetStatisticsDTO {

	private long total;
	private Map<String, PetCategoryStatisticsDTO> category = new HashMap<>();

	public void incrementTotal(long count) {
		this.total += count;
	}

	public PetCategoryStatisticsDTO getOrCreateCategory(String category) {
		return this.category.computeIfAbsent(category, _ -> new PetCategoryStatisticsDTO());
	}

}
