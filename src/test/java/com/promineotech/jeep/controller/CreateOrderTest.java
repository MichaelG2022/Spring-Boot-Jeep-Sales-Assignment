package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
// @formatter:off
@Sql(
    scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
               "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
// @formatter:on
public class CreateOrderTest {
	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;

	/*
	 * Creates the order body, generates the uri String, sets the Http headers media
	 * type to application JSON.
	 * 
	 * Uses the TestRestTemplate to create the response.
	 * 
	 * Tests that the returned status code is 201 (Created) and that the body is not
	 * null.
	 * 
	 * Tests that the response body matches what we originally set as the request
	 * body.
	 */
	@Test
	void testCreateOrderReturnsSuccess201() {

		// Given: an order as JSON
		String body = createOrderBody();
		String uri = String.format("http://localhost:%d/orders", serverPort);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

		// When: the order is sent
		ResponseEntity<Order> response = restTemplate.exchange(uri, HttpMethod.POST, bodyEntity, Order.class);

		// Then: a 201 status is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		// And: the returned order is correct
		assertThat(response.getBody()).isNotNull();

		// This puts the body into an order object, then compares the data in that
		// object to the data we
		// used to create the request body.
		Order order = response.getBody();
		assertThat(order.getCustomer().getCustomerId()).isEqualTo("MAYNARD_TORBJORG");
		assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.COMPASS);
		assertThat(order.getModel().getTrimLevel()).isEqualTo("Limited");
		assertThat(order.getModel().getNumDoors()).isEqualTo(4);
		assertThat(order.getColor().getColorId()).isEqualTo("EXT_OMAHA_ORANGE");
		assertThat(order.getEngine().getEngineId()).isEqualTo("3_6_HYBRID");
		assertThat(order.getTire().getTireId()).isEqualTo("265_MICHELIN");
		assertThat(order.getOptions()).hasSize(6);

	} // end testCreateOrderReturnsSuccess201

	// Generates the order body in application JSON
	protected String createOrderBody() {
	// @formatter:off
    return "{\n"
        + "  \"customer\":\"MAYNARD_TORBJORG\",\n"
        + "  \"model\":\"COMPASS\",\n"
        + "  \"trim\":\"Limited\",\n"
        + "  \"doors\":4,\n"
        + "  \"color\":\"EXT_OMAHA_ORANGE\",\n"
        + "  \"engine\":\"3_6_HYBRID\",\n"
        + "  \"tire\":\"265_MICHELIN\",\n"
        + "  \"options\":[\n"
        + "    \"DOOR_BESTOP_ELEMENT_MIRROR\",\n"
        + "    \"EXT_MOPAR_STEP_BLACK\",\n"
        + "    \"EXT_MOPAR_GRILLE\",\n"
        + "    \"EXT_WARN_WINCH\",\n"
        + "    \"WHEEL_MOPAR_SPARE\",\n"
        + "    \"INT_MOPAR_MATS\"\n"
        + "  ]\n"
        + "}";
    // @formatter:on
	} // end createOrderBody

} // end CLASS
