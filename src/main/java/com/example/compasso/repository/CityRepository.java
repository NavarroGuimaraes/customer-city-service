package com.example.compasso.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.compasso.model.City;

public interface CityRepository extends CrudRepository<City, Long>{
	
	@Query(value = "SELECT * FROM cities c " + 
		       " WHERE UPPER(c.name) like UPPER('%' || ?1 || '%') " +
		       " AND is_deleted = 0", nativeQuery = true)
	List<City> findByName(String name);
	
	@Query(value = "SELECT * FROM cities c " + 
		       " WHERE UPPER(c.state) like UPPER('%' || ?1 || '%') " +
		       " AND is_deleted = 0", nativeQuery = true)
	List<City> findByState(String state);
	
	public List<City> findByNameLikeAndIsDeletedIsFalse(String name);
	
	public 	City findFirstByNameIgnoreCaseAndIsDeletedIsFalse(String name);
	
	public Optional<City> findByIdAndIsDeletedIsFalse(Long id);

	
	@Modifying
	@Transactional
	@Query("UPDATE City c SET c.isDeleted = 1 WHERE c.id = ?1")
	int deleteCityById(Long id);

	@Query(value="SELECT c FROM City c"
			+ " WHERE c.name = ?1"
			+ " AND c.state = ?2")
	City findByNameAndState(String name, String state);

}
