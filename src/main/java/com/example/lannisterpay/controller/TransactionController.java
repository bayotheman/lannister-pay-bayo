package com.example.lannisterpay.controller;

import com.example.lannisterpay.classes.Computation;
import com.example.lannisterpay.classes.TransactionAssembler;
import com.example.lannisterpay.classes.Transaction;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TransactionController {

	private final TransactionAssembler assembler;
//	private final TransactionRepository transactionRepository;

	public TransactionController(TransactionAssembler assembler){
		this.assembler = assembler;
//		this.transactionRepository = transactionRepository;
	}

	@PostMapping("split-payments/compute")
	ResponseEntity<?> compute(@RequestBody Transaction transaction){
		Computation computation = new Computation(transaction);
		computation.compute();
		JSONPObject parser = new JSONPObject(computation.toString(), computation);
		EntityModel<Computation> entityModel =
				assembler.toModel(computation);
		return ResponseEntity.created(null).body(entityModel);
//		return computation.toString();
	}

}
