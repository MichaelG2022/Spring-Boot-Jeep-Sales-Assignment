package com.promineotech.jeep.service;

import java.util.List;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

/*
 * Service interface for interface-driven coding.
 */
public interface JeepSalesService {
	List<Jeep> fetchJeeps(JeepModel model, String trim);
} // end INTERFACE
