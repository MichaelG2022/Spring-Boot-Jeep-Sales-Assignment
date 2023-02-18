package com.promineotech.jeep.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * The four annotations used here are part of the Project Lombok java library tool to be able to more
 * quickly create boilerplate code, such as Getters and Setters, this.variable = variable, toString() methods,
 * empty constructors, constructors with all fields, and constructors for each field singularly..
 * The code for the boilerplate is not shown in the source but it does show in the compiled code in the .class file. 
 * 
 * Data annotation generates Getters and Setters, overrides the toString() method, and overrides
 * the equals() and hashCode() methods for all fields in the class. It also generates constructors
 * with single parameters for fields that require special handling. 
 * 
 * Builder annotation allows the properties of all fields of an object to be set with a single statement.
 * 
 * The Constructor annotations are self-explanatory.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jeep {
	private Long modelPK;
	private JeepModel modelId;
	private String trimLevel;
	private int numDoors;
	private int wheelSize;
	private BigDecimal basePrice;
} // end CLASS
