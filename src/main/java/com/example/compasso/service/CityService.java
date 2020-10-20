package com.example.compasso.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.compasso.model.City;
import com.example.compasso.repository.CityRepository;
import com.example.compasso.resource.data.request.CityRequestDTO;
import com.example.compasso.resource.data.response.CityResponseDTO;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private MapperFacade mapper;

	public ResponseEntity<?> findCityByName(String name) {

		try {

			List<City> cities = cityRepository.findByName(name);

			if (cities != null && !cities.isEmpty()) {

				//Map the cities to its dto
				List<CityResponseDTO> mappedCities = cities
						.stream()
						.map( c ->
						mapper.map(c, CityResponseDTO.class)
								).collect(Collectors.toList());

				return ResponseEntity.ok(mappedCities);

			} else {

				return ResponseEntity.notFound().build();

			}	

		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");

		}


	}

	public ResponseEntity<?> findCityById(Long id) {

		try {

			Optional<City> city = cityRepository.findByIdAndIsDeletedIsFalse(id);

			if (city.isPresent()) {

				//a city was returned
				CityResponseDTO mappedCity = mapper.map(city.get(), CityResponseDTO.class);
				return ResponseEntity.ok(mappedCity);

			} else {

				//no city with the given id was found.
				return ResponseEntity.notFound().build();

			}
		} catch (Exception e) {

			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");

		}
	}

	public ResponseEntity<?> save(CityRequestDTO cityRequestDTO) {

		try {
			
			City persistedCity = cityRepository.findByNameAndState(cityRequestDTO.getName()
					, cityRequestDTO.getState());
			
			if (persistedCity != null ) {
				
				//if city already exists, then we will just activate it. 
				if (persistedCity.isDeleted()) {
					
					persistedCity.setDeleted(false);
					persistedCity = cityRepository.save(persistedCity);
					return ResponseEntity
							.status(HttpStatus.OK)
							.body(mapper.map(persistedCity, CityResponseDTO.class));
					
				}
				
				return ResponseEntity.status(HttpStatus.CONFLICT).body("City Already Exists");
			}
			
			City city = mapper.map(cityRequestDTO, City.class);	
			city = cityRepository.save(city);

			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(mapper.map(city, CityResponseDTO.class));

		} catch (Exception e) {
			
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");
			
		}


	}

	public ResponseEntity<?> deleteCityById(Long id) {
		
		try {
			//the city is not actually removed to avoid integrity errors. 
			//It just updates the isRemoved property	
			int rowCount = cityRepository.deleteCityById(id);

			//if rowcount is 0 then no city was deleted.
			if (rowCount <= 0) return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");
			
		}
	}

	public ResponseEntity<?> findCityByState(String state) {
		
		try {

			List<City> cities = cityRepository.findByState(state);
			if (cities != null && !cities.isEmpty()) {

				//Map the cities to its dto
				List<CityResponseDTO> mappedCities = cities
						.stream()
						.map( c ->
						mapper.map(c, CityResponseDTO.class)
								).collect(Collectors.toList());

				return ResponseEntity.ok(mappedCities);

			} else {

				return ResponseEntity.notFound().build();

			}
		
		} catch (Exception e) {
			
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");

		}
		
	}
	
	public City findFirstCityEqualsIgnoreCaseAndIsDeletedIsFalse(String name) {
		return cityRepository.findFirstByNameIgnoreCaseAndIsDeletedIsFalse(name);
	}

}
