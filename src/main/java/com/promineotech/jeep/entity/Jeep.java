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
 * Data annotation does the following (simplified):
 *      @Getter @Setter(on non-final fields) @RequiredArgsConstructor @ToString @EqualsAndHashCode
 *      Since no args are required for this application, this generates a no-args constructor.
 * 
 * Builder annotation does the following: (In order of the outline)
 *      In the annotated class (Jeep), generates an all-args constructor.
 *          In this case, it changed the no-args constructor created by @Data so we
 *          had to add @NoArgsConstructor annotation.
 *      In the annotated class (Jeep), generates a builder() method to create a new instance of builder.
 *      In the annotated class (Jeep), generates the JeepBuilder class as an inner class.
 *      In the Builder class, generates private non-static, non-final fields
 *          for each parameter of the annotated class.
 *      In the Builder class, generates a package private no-args empty constructor.
 *      In the Builder class, generates a setter-like method for 
 *          each field (ex: this.modelPK = modelPK). The "this" is returned for each field 
 *          which allows the "setter" calls to be chained as shown in FetchJeepTestSupport.
 *      In the Builder class, generates a build() method which calls the setter-like methods,
 *          passing in each field.
 *      In the Builder class, generates a toString() override method.
 *      
 * @NoArgsConstructor generates a no-args constructor to replace the one that @Builder changed.
 *      Adding the no-args constructor breaks @Builder so we have to add an all-args
 *      constructor to fix @Builder.
 *      We added the no-args constructor for possible future use.
 *      
 * @AllArgsConstructor generates an all-args constructor to replace the one that got changed
 *      when we added the @NoArgsConstructor. This fixes @Builder
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
