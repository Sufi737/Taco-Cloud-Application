package com.springinaction.tacocloud.jpa.entities;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="Ingredient_Ref")
public class IngredientRef {
	private final String ingredient;
}
