package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.CochePost;

// esta clase implementa a JpaRepository y permite utilizar sus métodos (se le pasa el tipo de objeto y el tipo de la primary key)
public interface CochePostRepository extends JpaRepository<CochePost, Long>{

}
