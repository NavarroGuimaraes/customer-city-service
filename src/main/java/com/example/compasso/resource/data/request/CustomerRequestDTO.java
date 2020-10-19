package com.example.compasso.resource.data.request;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.compasso.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
		
	@NotEmpty(message = "name can't be empty")
	@Length(min = 2, max = 200, message = "the length must be between 5 and 200 characters")
	private String name;
	
	@Enumerated
	@NotNull
	private Gender gender;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonProperty(value="birth_date")
	@NotNull
	private Date birthDate;
	
	@JsonProperty("city_name")
	@NotEmpty(message="city name can't be empty")
	@Length(min = 2, max = 100, message = "the length must be between 2 and 100 characters")
	private String cityName;
	
}
