package com.springinaction.tacocloud.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springinaction.tacocloud.jpa.entities.Ingredient;
import com.springinaction.tacocloud.jpa.entities.Taco;
import com.springinaction.tacocloud.jpa.entities.TacoIngredientEntity;
import com.springinaction.tacocloud.jpa.entities.Ingredient.Type;
import com.springinaction.tacocloud.jpa.repositories.IngredientRepository;
import com.springinaction.tacocloud.jpa.repositories.TacoIngredientRepository;
import com.springinaction.tacocloud.jpa.repositories.TacoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	protected IngredientRepository ingredientRepo;
	protected TacoRepository tacoRepo;
	protected TacoIngredientRepository tacoIngredientRepo;
	
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, TacoIngredientRepository tacoIngredientRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
		this.tacoIngredientRepo = tacoIngredientRepo;
	}
	
	public List<Ingredient> getAllIngredients() {
		Iterable<Ingredient> ingredientsFromJdbc = ingredientRepo.findAll();
		//converting Iterable returned by findAll() to a List
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredientsFromJdbc.forEach(ingredients::add);
		return ingredients;
	}
	
	public void saveTacoIngredientMapping(List<String> ingredientsList, Long TacoId) {
//		String ingredients = String.join(",", ingredientsList);
		for (String ingredient: ingredientsList) {
			this.tacoIngredientRepo.save(new TacoIngredientEntity(TacoId, ingredient));
		}
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = getAllIngredients();
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));
		}
	}

	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("taco", new Taco());
		return "design";
	}
	
	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
	
	@PostMapping
	public String processTaco(@ModelAttribute("taco") Taco taco, @RequestParam HashMap<String, String> formData, Errors error) {
		
		//check for validation errors
		if (error.hasErrors()) {
			return "design";
		}
		// Save the taco...
		log.info("Processing taco: " + taco);
		tacoRepo.save(taco);
		//saving taco and its ingredients mapping
		List<Ingredient> ingredients = getAllIngredients();
		List<String> ingredientsAdded = new ArrayList<String>();
		for(Ingredient ig: ingredients) {
			String ingredientValue = formData.get(ig.getId());
			if (ingredientValue != null) {
				ingredientsAdded.add(ingredientValue);
			}
		}
		log.info("Ingredients selected: "+ingredientsAdded.toString());
		saveTacoIngredientMapping(ingredientsAdded, taco.getId());
 		return "redirect:/orders/current";
	}
}
