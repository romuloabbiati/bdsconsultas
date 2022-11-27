package com.devsuperior.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Native SQL
		List<EmpregadoDeptProjection> list = repository.search1();
		List<EmpregadoDeptDTO> listDTO = list.stream()
			.map(empregadoDeptProjection -> new EmpregadoDeptDTO(empregadoDeptProjection))
			.collect(Collectors.toList());
		
		System.out.println("\n*** Native SQL NOT IN");
		for(EmpregadoDeptDTO empregadoDTO : listDTO) {
			System.out.println(empregadoDTO);
		}
		
		System.out.println("\n\n");
		
		// JPQL
		List<EmpregadoDeptDTO> listJPQL = repository.search2();
		
		System.out.println("\n*** JPQL NOT IN");
		for(EmpregadoDeptDTO empregadoDTO : listDTO) {
			System.out.println(empregadoDTO);
		}
		
		// Native SQL LEFT JOIN
		List<EmpregadoDeptProjection> anotherList = repository.search3();
		List<EmpregadoDeptDTO> anotherListDTO = anotherList.stream()
			.map(empregadoDeptProjection -> new EmpregadoDeptDTO(empregadoDeptProjection))
			.collect(Collectors.toList());
		
		System.out.println("\n*** Native SQL LEFT JOIN");
		for(EmpregadoDeptDTO empregadoDTO : anotherListDTO) {
			System.out.println(empregadoDTO);
		}
	}
}
