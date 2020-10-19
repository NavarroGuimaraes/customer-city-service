package com.example.compasso.resource.data.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequestDTO {
	
	@NotEmpty(message = "name can't be empty")
	@Length(min = 2, max = 100, message = "the length must be between 2 and 100 characters")
	private String name;
	
	@NotEmpty(message = "state can't be empty")
	@Length(min = 2, max = 30, message = "the length must be between 2 and 30 characters")
	private String state;
}
