package com.example.compasso.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.example.compasso.resource.data.request.CityRequestDTO;

public class CityControllerTest {
	
	@Test
	void cityCreation() {
		//given
		CityRequestDTO request = CityRequestDTO
				.builder()
				.name("Recife")
				.state("Pernambuco")
				.build();
		CityRestController controller = new CityRestController();
		
		//when
		ResponseEntity<?> response = controller.save(request);
		
		//then
		assertEquals(response, request);
	}

}
