package com.example.compasso.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.compasso.model.City;
import com.example.compasso.repository.CityRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityControllerTest {
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private TestEntityManager entityManager;
	
	@Test
	void whenSavingValidCity_thenSaveAndReturnCityResponse() {
		//given
		String city_name = "Recife";
		String state = "Pernambuco";
		City city = City
				.builder()
				.name(city_name)
				.state(state)
				.build();
		
		entityManager.persist(city);
		entityManager.flush();
		
		//when
		City found = cityRepository.findFirstByNameIgnoreCaseAndIsDeletedIsFalse("Recife");
		
		//then
		assertEquals(found.getName(), city_name);
		assertEquals(found.getState(), state);		
	}

}
