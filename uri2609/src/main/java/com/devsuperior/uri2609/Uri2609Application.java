package com.devsuperior.uri2609;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import com.devsuperior.uri2609.repositories.CategoryRepository;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<CategorySumProjection> list = repository.search1();
		List<CategorySumDTO> listDTO = list.stream()
			.map(categorySumProjection -> new CategorySumDTO(categorySumProjection))
			.collect(Collectors.toList());
		
		// Native SQL
		System.out.println("\n*** Native SQL");
		for(CategorySumDTO categoryDTO : listDTO) {
			System.out.println(categoryDTO);
		}
		
		System.out.println("\n\n");
		
		List<CategorySumDTO> listJPQL = repository.search2();
		
		// JPQL
		System.out.println("\n*** JPQL");
		for(CategorySumDTO categoryDTO : listJPQL) {
			System.out.println(categoryDTO);
		}
	}
}
