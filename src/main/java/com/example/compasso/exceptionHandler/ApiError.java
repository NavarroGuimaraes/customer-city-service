package com.example.compasso.exceptionHandler;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

	private HttpStatus status;
	private String localizedMessage;
	private List<String> errors;

}
