package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

	@Query(nativeQuery = true, 
		   value = "SELECT empregados.cpf, empregados.enome, departamentos.dnome " + 
		   		"FROM empregados " + 
		   		"INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero " + 
		   		"WHERE empregados.cpf NOT IN ( " + 
		   		"	SELECT empregados.cpf " + 
		   		"	FROM empregados " + 
		   		"	INNER JOIN trabalha ON empregados.cpf = trabalha.cpf_emp " + 
		   		") " + 
		   		"ORDER BY empregados.cpf")
	List<EmpregadoDeptProjection> search1();
	
	@Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(empregado.cpf, empregado.enome, " + 
			"empregado.departamento.dnome) " + 
			"FROM Empregado empregado " + 
			"WHERE empregado.cpf NOT IN ( " + 
			"	SELECT empregado.cpf " + 
			"	FROM Empregado empregado " + 
			"	INNER JOIN empregado.projetosOndeTrabalha " + 
			") " + 
			"ORDER BY empregado.cpf")
	List<EmpregadoDeptDTO> search2();
	
	@Query(nativeQuery = true,
		   value = "SELECT empregados.cpf, empregados.enome, departamentos.dnome " + 
		   		"FROM empregados " + 
		   		"INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero " + 
		   		"LEFT JOIN trabalha ON empregados.cpf = trabalha.cpf_emp " + 
		   		"WHERE trabalha.cpf_emp IS NULL " + 
		   		"ORDER BY empregados.cpf")
	List<EmpregadoDeptProjection> search3();
	
}
