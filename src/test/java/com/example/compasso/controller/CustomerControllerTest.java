package com.example.compasso.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.compasso.model.City;
import com.example.compasso.model.Customer;
import com.example.compasso.model.enums.Gender;
import com.example.compasso.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerControllerTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    void whenSavingValidCustomer_thenSaveAndReturnCustomer() {
    	
		String city_name = "Olinda";
		String state = "Pernambuco";
    	String name = "Navarro Silva";
    	//gender 0 is "HOMEM"
    	//CHeck gender enum on the model package for more info.
    	Gender gender = Gender.HOMEM;
    	//gets 18 years ago date
    	Date birthDate = getEighteenYearsAgo();
    	short age = 18;
    	
		City city = City
				.builder()
				.name(city_name)
				.state(state)
				.build();
    	
    	Customer customer = Customer
    			.builder()
    			.age(age)
    			.name(name)
    			.birthDate(birthDate)
    			.gender(gender)
    			.city(city)
    			.build();
    	
    	entityManager.persist(city);
    	entityManager.persist(customer);
		entityManager.flush();
		
		//when
		Customer found = customerRepository.findFirstByNameContainingIgnoreCaseAndIsDeletedIsFalse(name);
		
		//then
		assertEquals(found.getName(), name);
		assertEquals(found.getBirthDate(), birthDate);
		assertEquals(found.getAge(), age);
		assertEquals(found.getGender().getCode(), gender.getCode());
		assertEquals(found.getCity().getName(), city.getName());
    	
    }

	private Date getEighteenYearsAgo() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -18);
		Date newDate = c.getTime();
		return newDate;
	}
}
