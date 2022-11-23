package com.devsuperior.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import com.devsuperior.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<ProductMinProjection> list = repository.search1(10, 20, "P");
		List<ProductMinDTO> listDTO = list.stream()
			.map(productMinProjection -> new ProductMinDTO(productMinProjection))
			.collect(Collectors.toList());
		
		// Native SQL
		System.out.println("\n*** Native SQL");
		for(ProductMinDTO productDTO : listDTO) {
			System.out.println(productDTO);
		}
		
		System.out.println("\n\n");
		
		List<ProductMinDTO> listJPQL = repository.search2(10, 20, "P");
		
		// JPQL
		System.out.println("\n*** JPQL");
		for(ProductMinDTO productDTO : listJPQL) {
			System.out.println(productDTO);
		}
	}
}
