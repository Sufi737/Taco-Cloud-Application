package com.springinaction.tacocloud.jpa.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TacoIngredientRelation implements Serializable{

	private Long tacoId;
	private String ingredient;
	
	public TacoIngredientRelation(Long tacoId, String ingredient) {
		this.tacoId = tacoId;
		this.ingredient = ingredient;
	}
}
