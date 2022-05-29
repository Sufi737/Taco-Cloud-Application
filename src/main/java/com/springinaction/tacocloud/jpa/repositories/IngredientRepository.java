package com.springinaction.tacocloud.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springinaction.tacocloud.jpa.entities.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{

	/*
	 * Repositories for Spring Data JDBC and Spring Data JPA are identical
	 * However methods are not required for Spring Data JPA. Below methods were required for Spring Data JDBC
	 * */
//	public Optional<Ingredient> getById(String id);
//	
//	public Iterable<Ingredient> findAll();
//	
//	public Ingredient save(Ingredient ingredient);
}
