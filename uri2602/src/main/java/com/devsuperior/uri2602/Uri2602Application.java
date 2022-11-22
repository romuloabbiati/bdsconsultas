package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//
		List<CustomerMinProjection> list = customerRepository.search1("rs");
		List<CustomerMinDTO> listDTO = list.stream()
					.map(customerMinProjection -> new CustomerMinDTO(customerMinProjection))
					.collect(Collectors.toList());
		
		// Native SQL
		System.out.println("\n*** Native SQL");
		for(CustomerMinDTO customerDTO : listDTO) {
			System.out.println(customerDTO);
		}
		
		System.out.println("\n\n");
		
		List<CustomerMinDTO> listDTO2 = customerRepository.search2("rs");
		
		// JPQL
		System.out.println("\n*** JPQL");
		for(CustomerMinDTO customerDTO : listDTO2) {
			System.out.println(customerDTO);
		}
	}

}
