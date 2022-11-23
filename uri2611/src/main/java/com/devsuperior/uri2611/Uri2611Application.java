package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<MovieMinProjection> list = movieRepository.search1("Action");
		List<MovieMinDTO> listDTO = list.stream()
					.map(movieMinProjection -> new MovieMinDTO(movieMinProjection))
					.collect(Collectors.toList());
		
		// Native SQL
		System.out.println("\n*** Native SQL");
		for(MovieMinDTO movieMin : listDTO) {
			System.out.println(movieMin);
		}
		
		System.out.println("\n\n");
		
		// JPQL
		List<MovieMinDTO> listJPQL = movieRepository.search2("Action");
		
		System.out.println("\n*** JPQL");
		for(MovieMinDTO movieDTO : listJPQL) {
			System.out.println(movieDTO);
		}
	}
}
