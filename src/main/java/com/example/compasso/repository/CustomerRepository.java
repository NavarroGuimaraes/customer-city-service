package com.example.compasso.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.compasso.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	List<Customer> findByNameContainingIgnoreCaseAndIsDeletedIsFalse(String name);
	
	Optional<Customer> findByIdAndIsDeletedIsFalse(Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Customer c SET c.isDeleted = 1 WHERE c.id = ?1")
	int deleteCustomerById(Long id);

}
