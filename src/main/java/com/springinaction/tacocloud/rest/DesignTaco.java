//package com.springinaction.tacocloud.rest;
//
//import org.springframework.data.domain.PageRequest;
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.springinaction.tacocloud.jpa.entities.Taco;
//import com.springinaction.tacocloud.jpa.repositories.TacoRepository;
//
//@RestController
//@RequestMapping(path="/design", produces="application/json")
//@CrossOrigin(origins="*")
//public class DesignTaco {
//	
//	private TacoRepository tacoRepo;
//
//	public DesignTaco(TacoRepository tacoRepo) {
//		this.tacoRepo = tacoRepo;
//	}
//
//	@GetMapping("/recent")
//	public Iterable<Taco> getRecentTacos(@RequestParam(name="numberOfTacos", required=false) Integer numberOfTacos) {
//		Iterable<Taco> tacos;
//		if (numberOfTacos != null) {
//			tacos = this.tacoRepo.findAll(PageRequest.of(0, (int)numberOfTacos)).getContent();
//		} else {
//			tacos = this.tacoRepo.findAll();
//		}
//		return tacos;
//	}
//	
//	@GetMapping("/recentwithresources")
//	public CollectionModel<EntityModel<Taco>> getRecentTacosWithResources(@RequestParam(name="numberOfTacos", required=false) Integer numberOfTacos) {
//		Iterable<Taco> tacos;
//		if (numberOfTacos != null) {
//			tacos = this.tacoRepo.findAll(PageRequest.of(0, (int)numberOfTacos)).getContent();
//		} else {
//			tacos = this.tacoRepo.findAll();
//		}
//		CollectionModel<EntityModel<Taco>> recentResources = CollectionModel.wrap(tacos);
//		recentResources.add(WebMvcLinkBuilder.linkTo(DesignTaco.class)
//				.slash("recent")
//				.withRel("recents"));
//		recentResources.add(WebMvcLinkBuilder.linkTo(DesignTaco.class)
//				.slash("something/random")
//				.withRel("random"));
//	
//		return recentResources;
//	}
//	
//	@PostMapping(consumes="application/json")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Taco addTaco(@RequestBody Taco taco) {
//		return this.tacoRepo.save(taco);
//	}
//}
