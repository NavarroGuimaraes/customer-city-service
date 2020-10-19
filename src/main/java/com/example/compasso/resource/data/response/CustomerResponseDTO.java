package com.example.compasso.resource.data.response;

import java.util.Date;

import com.example.compasso.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {	
	private Long id;
	
	private String name;
	
	private Gender gender;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value="birth_date")
	private Date birthDate;
	
	private short age;
	
	@JsonProperty(value="city_name")
	private String cityName;
	
	private String state;
}
