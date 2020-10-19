package com.example.compasso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.compasso.resource.data.request.CustomerRequestDTO;
import com.example.compasso.resource.data.request.UpdateCustomerRequestDTO;
import com.example.compasso.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(description="Serviços para operações com cidade.")
@RestController
@RequestMapping("/customer")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value="Persiste o cliente na base de dados")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid CustomerRequestDTO customerRequestDTO){
		try {
			return customerService.save(customerRequestDTO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@ApiOperation(value="Retorna os Clientes que possuem o nome passado como parâmetro")
	@GetMapping("/name/{name}")
	public ResponseEntity<?> getCustomerByName(@PathVariable String name){
		
		try {
			return customerService.getCustomersByName(name);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@ApiOperation(value="Retorna o cliente que possui o id passado como parâmetro")
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerByName(@PathVariable Long id){
		
		try {
			return customerService.getCustomerById(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@ApiOperation(value="Remove o cliente que possui o id passado como parâmetro")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
		
		try {
			return customerService.deleteCustomerById(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@ApiOperation(value="Altera o cliente passado como parâmetro")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateCustomerRequestDTO customerDTO){
		
		try {
			return customerService.update(customerDTO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	

}
