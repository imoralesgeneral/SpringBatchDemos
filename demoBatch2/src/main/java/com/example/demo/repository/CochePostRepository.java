package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.CochePost;

public interface CochePostRepository extends JpaRepository<CochePost, Long>{

	/**
	 * Método que realizará la búsqueda en la BD por la query indicada
	 * @param marca
	 * @return
	 */
	@Query("select u from CochePost u where u.marca like %?1")
	List<CochePost> findByMarca(String marca);
	
}
