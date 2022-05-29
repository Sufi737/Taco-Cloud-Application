package com.springinaction.tacocloud.resource.assemblers;

import java.util.Date;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import com.springinaction.tacocloud.jpa.entities.Taco;

import lombok.Getter;

@Relation(value="taco", collectionRelation="tacos")
public class TacoEntityModel extends EntityModel<TacoEntityModel>{
	
	@Getter
	private final String name;
	
	@Getter
	private final Date createdAt;

	public TacoEntityModel(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
	}
}

/*
 * 
 * The above class is just to convert Taco to TacoEntityModel which is a type of EntityModel
 * So that Taco provides hyperlinks in API response
 */