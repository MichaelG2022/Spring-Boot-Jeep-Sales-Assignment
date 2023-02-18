package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

/*
 * SpringBootTest annotation does many things, but at our level, the most important thing for us to know
 * is that it extends the JUnit Test Framework so that Spring Boot is in control of the test. 
 * 
 * The webEnvironment tells Spring to run in a web environment and to use random ports so tests don't try
 * to run on the same port every time.
 * 
 * ActiveProfiles annotation tells Spring which active bean definition profile to use when loading
 * an Application Context for test classes. We haven't discussed beans yet.
 * 
 * Sql annotation is used to annotate a test class or test method to configure SQL scripts and statements
 * to be executed against a given database during integration tests. I'm sure this will become clear soon.
 * 
 * SqlConfig annotation tells Spring how to parse sql scripts.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
// @formatter:off
@Sql(scripts = { 
	"classpath:flyway/migrations/V1.0_Jeep_Schema.sql",
	"Classpath:flyway/migrations/V1.1_Jeep_Data.sql" }, 
	config = @SqlConfig(encoding = "utf-8"))
// @formatter:on
class FetchJeepTest {

	/*
	 * Autowired annotation tells Spring Boot to inject the TestRestTemplate it has
	 * created in the Application Context into the variable following. An instance
	 * of dependency injection.
	 * 
	 * TestRestTemplate allows us to send HTTP to the running application. It acts
	 * as a REST server we can use to test applications using Spring Boot.
	 */
	@Autowired
	private TestRestTemplate restTemplate;

	/*
	 * LocalServerPort annotation tells Spring Boot to fill in, or inject, the port
	 * it has created in the Application Context into the variable following. An
	 * instance of dependency injection.
	 */
	@LocalServerPort
	private int serverPort;

	/*
	 * Tests that we can send an HTTP GET request and receive the proper response.
	 * 
	 * ResponseEntity adds an HTTP status code to the response generated after
	 * exchange sends the GET request to the restTemplate through the uri. The null
	 * means no HTTP entity is sent, and the ParameterizedTypeReference is used to
	 * pass generic type information.
	 * 
	 * This method tests that the Test Rest Server returns a status of 200, signifying
	 * that the test was successful.
	 */
	@Test
	void assertThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Sport";
		String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);

		ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {
				});

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	} // end assertThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied

} // end CLASS
