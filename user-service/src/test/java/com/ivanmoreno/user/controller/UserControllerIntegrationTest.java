package com.ivanmoreno.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@Test
	void testGetById() {
		
		Map<String, Object> response = 
				restTemplate.getForObject("http://localhost:" + port + "/v1/user/1", Map.class);
		
		assertNotNull(response);
		
		//Asserting API Response
		String id = response.get("id").toString();
		assertNotNull(id);
		assertEquals("1", id);
		
		String name = response.get("name").toString();
		assertNotNull(name);
		assertEquals("User Name 1", name);
		
		Boolean isModified = (Boolean) response.get("isModified");
		assertNull(isModified);
	}
	
	@Test
	void testGetById_NoContent() {
		
		ResponseEntity<Map> responseE = 
				restTemplate.getForEntity("http://localhost:" + port + "/v1/user/666", Map.class);
		
		assertNotNull(responseE);
		
		// Should return no content as there is no restaurant with id 666
		assertEquals(HttpStatus.NO_CONTENT, responseE.getStatusCode());
	}
	
	@Test
	void testGetByName() {
		
		Map<String, Object> requestParam = new HashMap<>();
		requestParam.put("name", "User Name 1");
		
		ResponseEntity<List> responseEntity = 
				restTemplate.exchange("http://localhost:" + port + "/v1/user?name={name}", HttpMethod.GET, null, List.class, requestParam);
		
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		List<Map<String, Object>> responses = responseEntity.getBody();
		assertNotNull(responses);
		
		// Assumed only single instance exist for user name contains "User Name 1"
		assertTrue(responses.size() == 1);
		
		Map<String, Object> response = responses.get(0);
		
		String id = response.get("id").toString();
		assertNotNull(id);
		assertEquals("1", id);
		
		String name = response.get("name").toString();
		assertNotNull(name);
		assertEquals("User Name 1", name);
		
		Boolean isModified = (Boolean) response.get("isModified");
		assertNull(isModified);
	}

	@Test
	void testAdd() {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("name", "User Name 4");
		requestBody.put("id", "4");
		requestBody.put("address", "address for 4 user");
		requestBody.put("city", "City 4");
		requestBody.put("phoneNo", "44444");
		
		ResponseEntity<Map> responseE = 
				restTemplate.postForEntity("http://localhost:" + port + "/v1/user", requestBody, Map.class);
		
		assertNotNull(responseE);
		
		// Should return created (status code 201)
	    assertEquals(HttpStatus.CREATED, responseE.getStatusCode());
		
	  //validating the newly created restaurant using API call
	    Map<String, Object> response
	        = restTemplate.getForObject("http://localhost:" + port + "/v1/user/4", Map.class);
	    
	    assertNotNull(response);
	    
	    //Asserting API Response
	    String id = response.get("id").toString();
	    assertNotNull(id);
	    assertEquals("4", id);
	    
	    String name = response.get("name").toString();
	    assertNotNull(name);
	    assertEquals("User Name 4", name);
	    
	    String address = response.get("address").toString();
	    assertNotNull(address);
	    assertEquals("address for 4 user", address);
	    
	    Boolean isModified = (Boolean) response.get("isModified");
		assertNull(isModified);
		
		String city = response.get("city").toString();
		assertNotNull(city);
		assertEquals("City 4", city);
		
		String phoneNo = response.get("phoneNo").toString();
		assertNotNull(phoneNo);
		assertEquals("44444", phoneNo);
	}
}
