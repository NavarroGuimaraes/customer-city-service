package com.example.compasso.config.mapper;
import org.springframework.stereotype.Component;

import com.example.compasso.model.City;
import com.example.compasso.model.Customer;
import com.example.compasso.resource.data.request.CityRequestDTO;
import com.example.compasso.resource.data.request.CustomerRequestDTO;
import com.example.compasso.resource.data.request.UpdateCustomerRequestDTO;
import com.example.compasso.resource.data.response.CityResponseDTO;
import com.example.compasso.resource.data.response.CustomerResponseDTO;
import com.example.compasso.util.LocalDateTimeConverter;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
@Component
public class ResourceMapper extends ConfigurableMapper{

	
	@Override
	protected void configure(MapperFactory factory) {
		factory.getConverterFactory().registerConverter(new LocalDateTimeConverter());
		
		factory.classMap(City.class, CityRequestDTO.class)
		    .byDefault()
		    .register();

		factory.classMap(City.class, CityResponseDTO.class)
	    	.byDefault()
	    	.register();
		
		factory.classMap(Customer.class, CustomerRequestDTO.class)
    	.byDefault()
    	.register();
		
		//maps the response to customer class to fill
		//state and name properties
		factory.classMap(Customer.class, CustomerResponseDTO.class)
		       .customize(new CustomMapper<Customer, CustomerResponseDTO>() {
		    	   @Override
		    	   public void mapAtoB(Customer a, CustomerResponseDTO b, MappingContext context) { 
		    			   b.setState(a.getCity().getState());
		    			   b.setCityName(a.getCity().getName());
		    		  
		    	   }
		       }).byDefault().register();
		
		//maps update dto to customer to fill
		//the id;
		factory.classMap(UpdateCustomerRequestDTO.class, Customer.class)
	       .customize(new CustomMapper<UpdateCustomerRequestDTO, Customer>() {
	    	   @Override
	    	   public void mapAtoB(UpdateCustomerRequestDTO a, Customer b, MappingContext context) { 
	    			   b.setId(a.getId());
	    		  
	    	   }
	       }).byDefault().register();
	}
	


}
