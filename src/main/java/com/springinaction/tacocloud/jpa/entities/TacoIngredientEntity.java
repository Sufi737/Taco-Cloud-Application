package com.springinaction.tacocloud.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@IdClass(TacoIngredientRelation.class)
@RequiredArgsConstructor
public class TacoIngredientEntity {
	@Id
	@NonNull()
    private Long tacoId;

    @Id
    @NonNull()
    private String ingredient;

	public TacoIngredientEntity() {
	}
}
