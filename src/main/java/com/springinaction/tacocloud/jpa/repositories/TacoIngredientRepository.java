package com.springinaction.tacocloud.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springinaction.tacocloud.jpa.entities.TacoIngredientEntity;
import com.springinaction.tacocloud.jpa.entities.TacoIngredientRelation;

public interface TacoIngredientRepository extends CrudRepository<TacoIngredientEntity, TacoIngredientRelation> {

}
