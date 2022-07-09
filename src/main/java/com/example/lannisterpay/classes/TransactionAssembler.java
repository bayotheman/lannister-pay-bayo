package com.example.lannisterpay.classes;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TransactionAssembler implements RepresentationModelAssembler<Computation, EntityModel<Computation>> {
	@Override
	public EntityModel<Computation> toModel(Computation entity) {
		return EntityModel.of(entity);
	}
}
