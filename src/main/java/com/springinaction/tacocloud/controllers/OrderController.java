package com.springinaction.tacocloud.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.springinaction.tacocloud.TacoConfigHolder;
import com.springinaction.tacocloud.jpa.entities.TacoOrder;
import com.springinaction.tacocloud.jpa.entities.User;
import com.springinaction.tacocloud.jpa.repositories.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private OrderRepository orderRepository;
	private TacoConfigHolder configHolder;

	public OrderController(
			OrderRepository orderRepository,
			TacoConfigHolder configHolder
	) {
		this.orderRepository = orderRepository;
		this.configHolder = configHolder;
	}

	@GetMapping("/current")
	public String processOrder(Model model) {
		model.addAttribute("tacoOrder", new TacoOrder());
		return "order_submit_form";
	}
	
	@PostMapping
	public String submitOrder(@Valid TacoOrder tacoOrder, Errors error, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if (error.hasErrors()) {
			return "order_submit_form";
		}
		tacoOrder.setPlacedAt(new Date());
		tacoOrder.setUser(user);
		log.info("Submitted order: "+tacoOrder);
		//need to save order to database
		this.orderRepository.save(tacoOrder);
		sessionStatus.setComplete();
		
		//just testing one config I created
		System.out.println("Page size: "+this.configHolder.getPageSize());
		
		return "redirect:/";
	}
	
}
