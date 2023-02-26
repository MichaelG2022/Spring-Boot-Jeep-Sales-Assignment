package com.promineotech.jeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;

import lombok.extern.slf4j.Slf4j;

/*
 * RestController annotation tells Spring Boot that the controller interface this controller class implements
 * is a REST controller. This cannot be done in the interface; it must be done in a class. 
 * The implementation of JeepSalesController means Spring Boot will then go look in that Interface.
 * 
 * Slf4j annotation enables Lombok logging. Log entry is made at info level showing the model and trim of the jeep model being requested
 */
@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {

	@Autowired
	private JeepSalesService jeepSalesService;

	@Override
	public List<Jeep> fetchJeeps(JeepModel model, String trim) {
		log.info("model={}, trim={}", model, trim);
		return jeepSalesService.fetchJeeps(model, trim);
	}

} // end CLASS
