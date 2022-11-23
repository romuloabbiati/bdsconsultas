package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, 
			value = "SELECT products.name " + 
					"FROM products " + 
					"INNER JOIN providers ON products.id_providers = providers.id " + 
					"WHERE products.amount BETWEEN :min and :max " + 
					"AND providers.name LIKE CONCAT(:firstLetter, '%')")
	List<ProductMinProjection> search1(Integer min, Integer max, String firstLetter);
	
	@Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(product.name) " + 
			"FROM Product product " + 
			"WHERE product.amount BETWEEN :min and :max " + 
			"AND product.provider.name LIKE CONCAT(:firstLetter, '%')")
	List<ProductMinDTO> search2(Integer min, Integer max, String firstLetter);
}
