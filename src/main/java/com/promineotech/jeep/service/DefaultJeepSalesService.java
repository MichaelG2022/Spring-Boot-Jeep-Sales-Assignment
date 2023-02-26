package com.promineotech.jeep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import lombok.extern.slf4j.Slf4j;

/*
 * Service annotation tells Spring Boot that this class is designated as a service class.
 * 
 * Slf4j annotation enables Lombok logging. The info log line is to show when this service class is called during testing.
 */
@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {

	@Override
	public List<Jeep> fetchJeeps(JeepModel model, String trim) {
	    log.info("The fetchJeeps method was called with model={} and trim={}", model, trim);
		return null;
	}

} // end CLASS
