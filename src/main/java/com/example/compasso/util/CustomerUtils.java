package com.example.compasso.util;

import java.util.Calendar;
import java.util.Date;

import com.example.compasso.model.City;
import com.example.compasso.model.Customer;

public class CustomerUtils {

	public static int calculateAge(Date birthDate) {		
		Calendar date = Calendar.getInstance();
		date.setTime(birthDate);
		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - date.get(Calendar.YEAR);

		if (today.get(Calendar.MONTH) < date.get(Calendar.MONTH)) {

			age--;
		} else {
			if (today.get(Calendar.MONTH) == date.get(Calendar.MONTH)
					&& today.get(Calendar.DAY_OF_MONTH) < date.get(Calendar.DAY_OF_MONTH)) {
				age--;
			}
		}

		return age;
	}
	
	public static Customer buildCostumer(Customer customer, City city) {
		short age = (short) calculateAge(customer.getBirthDate());
		customer.setAge(age);
		customer.setCity(city);
		return customer;
	}

}
