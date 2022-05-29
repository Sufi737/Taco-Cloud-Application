package com.springinaction.tacocloud.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springinaction.tacocloud.jpa.entities.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{

	/*
	 * Repositories for Spring Data JDBC and Spring Data JPA are identical
	 * However methods are not required for Spring Data JPA. Below methods were required for Spring Data JDBC
	 * */
//	public TacoOrder save(TacoOrder order);
	
}
