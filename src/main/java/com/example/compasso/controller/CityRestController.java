package com.example.compasso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.compasso.resource.data.request.CityRequestDTO;
import com.example.compasso.service.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(description="Serviços para operações com cidade.")
@RestController
@RequestMapping("/city")
public class CityRestController {
	
	@Autowired
	private CityService cityService;
	
	@ApiOperation(value="Retorna as cidades cujos nomes contiverem a string passada como parâmetro")
	@GetMapping("/name/{name}")
	public ResponseEntity<?> getCityByName(@PathVariable String name){
		
		try {
			return cityService.findCityByName(name);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@ApiOperation(value="Retorna as cidades cujos estados contiverem a string passada como parâmetro")
	@GetMapping("/state/{state}")
	public ResponseEntity<?> getCityByState(@PathVariable String state){
		
		try {
			return cityService.findCityByState(state);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@ApiOperation(value="Retorna a cidade cujo id for igual ao fornecido como parâmetro")
	@GetMapping("{id}")
	public ResponseEntity<?> getCityById(@PathVariable Long id){
		try {
			return cityService.findCityById(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@ApiOperation(value="Cria a cidade de caordo com os dados fornecidos")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid CityRequestDTO cityRequestDTO) {
		try {
			return cityService.save(cityRequestDTO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@ApiOperation(value="Realiza a remoção lógica da cidade do id passado como parâmetro")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCityById(@PathVariable Long id){
		try {
			return cityService.deleteCityById(id);
 		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
 		}
	}
}
