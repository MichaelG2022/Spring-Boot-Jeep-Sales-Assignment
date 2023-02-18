package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * SpringBootApplication annotation tells Spring Boot that this is a Spring Boot application.
 * Then the Main method tells SpringApplication to run this class.
 */
@SpringBootApplication
public class JeepSales {

	public static void main(String[] args) {
		SpringApplication.run(JeepSales.class, args);
	} // end MAIN

} // end CLASS
