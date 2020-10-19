package com.example.compasso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.compasso.model.City;
import com.example.compasso.model.Customer;
import com.example.compasso.repository.CustomerRepository;
import com.example.compasso.resource.data.request.CustomerRequestDTO;
import com.example.compasso.resource.data.request.UpdateCustomerRequestDTO;
import com.example.compasso.resource.data.response.CustomerResponseDTO;
import com.example.compasso.util.CustomerUtils;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CityService cityService;

	@Autowired
	private MapperFacade mapper;

	public ResponseEntity<?> save(CustomerRequestDTO customerRequestDTO) {
		
		try {
			
			City city = cityService.findFirstCityEqualsIgnoreCase(customerRequestDTO.getCityName());

			if (city == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City not found");
			}

			Customer customer = mapper.map(customerRequestDTO, Customer.class);

			customer = customerRepository.save(CustomerUtils.buildCostumer(customer, city));

			return ResponseEntity.ok(mapper.map(customer, CustomerResponseDTO.class));
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");

		}		
	}

	public ResponseEntity<?> getCustomersByName(String name) {

		try {
			
			List<Customer> customers  = customerRepository.findByNameContainingIgnoreCaseAndIsDeletedIsFalse(name);
			List<CustomerResponseDTO> mappedCustomers = new ArrayList<CustomerResponseDTO>();
			if (customers != null && !customers.isEmpty() ) {

				mappedCustomers = customers
						.stream()
						.map(c -> mapper.map(c, CustomerResponseDTO.class))
						.collect(Collectors.toList());	

			}

			return ResponseEntity.ok(mappedCustomers);
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");

		}
	}

	public ResponseEntity<?> getCustomerById(Long id) {

		try {
			
			Optional<Customer> customer = customerRepository.findByIdAndIsDeletedIsFalse(id);
			if (customer.isPresent()) {
				
				CustomerResponseDTO mappedCustomer = mapper.map(customer.get(), CustomerResponseDTO.class);
				return ResponseEntity.ok(mappedCustomer);
				
			} else {
				return ResponseEntity.notFound().build();
			}
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
			
		}
	}

	public ResponseEntity<?> deleteCustomerById(Long id) {
		try {
			
			//the customer is not actually removed to avoid integrity errors. 
			//It just updates the isRemoved property	
			int rowCount = customerRepository.deleteCustomerById(id);
			
			//if rowCount is 0 then no customer was modified
			if (rowCount <= 0) return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
			
			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			
			log.error(e.getMessage());			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
			
		}
	}

	public ResponseEntity<?> update(UpdateCustomerRequestDTO customerDTO) {
		try {
			
			//checks if the city exists
			City city = cityService.findFirstCityEqualsIgnoreCase(customerDTO.getCityName());

			if (city == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
			}
			
			//check if the customer exists
			Optional<Customer> persistedCustomer = customerRepository.findById(customerDTO.getId());
			
			if (!persistedCustomer.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
			}

			Customer customer = mapper.map(customerDTO, Customer.class);			
			customer.setCreatedAt(persistedCustomer.get().getCreatedAt());

			customer = customerRepository.save(CustomerUtils.buildCostumer(customer, city));

			return ResponseEntity.ok(mapper.map(customer, CustomerResponseDTO.class));
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
			
		}
	}



}
