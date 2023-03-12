package com.promineotech.jeep.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.jeep.dao.JeepOrderDao;
import com.promineotech.jeep.entity.Color;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.Option;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Tire;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepOrderService implements JeepOrderService {

	@Autowired
	private JeepOrderDao jeepOrderDao;

	/*
	 * Generates an order based on the request body created in CreateOrderTest,
	 * simulating the data a user would have sent to this application.
	 * 
	 * Uses that order object for data to save the order data to the database.
	 * 
	 */

	@Transactional
	@Override
	public Order createOrder(OrderRequest orderRequest) {
		log.info("Order={}", orderRequest);

		Customer customer = getCustomer(orderRequest);
		Jeep jeep = getModel(orderRequest);
		Color color = getColor(orderRequest);
		Engine engine = getEngine(orderRequest);
		Tire tire = getTire(orderRequest);
		List<Option> options = getOption(orderRequest);
		BigDecimal price = jeep.getBasePrice().add(color.getPrice()).add(engine.getPrice()).add(tire.getPrice());

		for (Option option : options) {
			price = price.add(option.getPrice());
		} // end FOR

		return jeepOrderDao.saveOrder(customer, jeep, color, engine, tire, price, options);
	} // end createOrder

	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	private List<Option> getOption(OrderRequest orderRequest) {
		return jeepOrderDao.fetchOptions(orderRequest.getOptions());
	} // end getOption

	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	private Tire getTire(OrderRequest orderRequest) {
		return jeepOrderDao.fetchTire(orderRequest.getTire()).orElseThrow(
				() -> new NoSuchElementException("Tire with ID=" + orderRequest.getTire() + " was not found"));
	} // end getTire

	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	private Engine getEngine(OrderRequest orderRequest) {
		return jeepOrderDao.fetchEngine(orderRequest.getEngine()).orElseThrow(
				() -> new NoSuchElementException("Engine with ID=" + orderRequest.getEngine() + " was not found"));
	} // end getEngine

	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	private Color getColor(OrderRequest orderRequest) {
		return jeepOrderDao.fetchColor(orderRequest.getColor()).orElseThrow(
				() -> new NoSuchElementException("Color with ID=" + orderRequest.getColor() + " was not found"));
	} // end getColor

	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	private Jeep getModel(OrderRequest orderRequest) {
		return jeepOrderDao.fetchModel(orderRequest.getModel(), orderRequest.getTrim(), orderRequest.getDoors())
				.orElseThrow(() -> new NoSuchElementException("Model with ID=" + orderRequest.getModel() + ", trim="
						+ orderRequest.getTrim() + orderRequest.getDoors() + " was not found"));
	} // end getModel

	/**
	 * 
	 * @param orderRequest
	 * @return
	 */
	private Customer getCustomer(OrderRequest orderRequest) {
		return jeepOrderDao.fetchCustomer(orderRequest.getCustomer()).orElseThrow(
				() -> new NoSuchElementException("Customer with ID=" + orderRequest.getCustomer() + " was not found"));
	} // end getCustomer

} // end CLASS
