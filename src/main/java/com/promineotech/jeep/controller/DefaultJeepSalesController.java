package com.promineotech.jeep.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

/*
 * RestController annotation tells Spring Boot that the controller interface this controller class implements
 * is a REST controller. This cannot be done in the interface; it must be done in a class. 
 * The implementation of JeepSalesController means Spring Boot will then go look in that Interface.
 */
@RestController
public class DefaultJeepSalesController implements JeepSalesController {

	@Override
	public List<Jeep> fetchJeeps(JeepModel model, String trim) {

		return null;
	}

} // end CLASS
