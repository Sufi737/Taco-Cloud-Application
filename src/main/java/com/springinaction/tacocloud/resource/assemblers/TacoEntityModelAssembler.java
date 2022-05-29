//package com.springinaction.tacocloud.resource.assemblers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
//
//import com.springinaction.tacocloud.jpa.entities.Taco;
////import com.springinaction.tacocloud.rest.DesignTaco;
//
//public class TacoEntityModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoEntityModel>{
//
////	public TacoEntityModelAssembler() {
////		super(DesignTaco.class, TacoEntityModel.class);
////	}
//	
//	@Override
//	protected TacoEntityModel instantiateModel(Taco taco) {
//		return new TacoEntityModel(taco);
//	}
//		
//	@Override
//	public TacoEntityModel toModel(Taco taco) {
//		return createModelWithId(taco.getId(), taco);
//	}
//	
//	public List<TacoEntityModel> toModel(Iterable<Taco> tacos) {
//		List<TacoEntityModel> tacoModels = new ArrayList<TacoEntityModel>();
//		for(Taco taco: tacos) {
//			tacoModels.add(createModelWithId(taco.getId(), taco));
//		}
//		return tacoModels;
//	}
//	
//}
///*
// * We use the toModel function in the controller which returns a list of TacoEntityModel
// */
