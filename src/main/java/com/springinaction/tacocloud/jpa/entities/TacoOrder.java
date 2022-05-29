package com.springinaction.tacocloud.jpa.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name="Taco_Order")
public class TacoOrder {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="order_generator", sequenceName = "order_seq")
	private Long id;
	
	@Column(name="placed_at")
	private Date placedAt;

	@NotBlank(message="Delivery name is required")
	@Column(name="delivery_Name")
	private String deliveryName;
	
	@NotBlank(message="Street is required")
	@Column(name="delivery_Street")
	private String deliveryStreet;
	
	@NotBlank(message="City is required")
	@Column(name="delivery_City")
	private String deliveryCity;
	
	@NotBlank(message="State is required")
	@Column(name="delivery_State")
	private String deliveryState;
	
	@NotBlank(message="Zip code is required")
	@Column(name="delivery_Zip")
	private String deliveryZip;
	
	@CreditCardNumber(message="Not a valid credit card number")
	@Column(name="cc_number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message="Must be formatted MM/YY")
	@Column(name="cc_expiration")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	@Column(name="cc_cvv")
	private String ccCVV;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Taco> tacos;
	
	@ManyToOne
	private User user;
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}
