package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	// Native SQL
	@Query(nativeQuery = true, 
			value = "SELECT name "
			+ "FROM customers "
			+ "WHERE UPPER(state) = UPPER(:state)")
	List<CustomerMinProjection> search1(String state);
	
	// JPQL
	@Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(customer.name)"
			+ "FROM Customer customer "
			+ "WHERE UPPER(customer.state) = UPPER(:state)")
	List<CustomerMinDTO> search2(String state);
}
