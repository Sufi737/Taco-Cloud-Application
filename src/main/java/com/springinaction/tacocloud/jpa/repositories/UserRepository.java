package com.springinaction.tacocloud.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springinaction.tacocloud.jpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
