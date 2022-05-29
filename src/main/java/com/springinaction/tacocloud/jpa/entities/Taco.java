package com.springinaction.tacocloud.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Data
@Entity
@Table(name="Taco")
@RestResource(rel="tacos", path="tacos")
public class Taco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="taco_generator", sequenceName = "taco_seq")
	private Long id;
	
	private Date createdAt = new Date();
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
}
