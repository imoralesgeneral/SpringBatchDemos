package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Coche;

public interface CocheRepository extends JpaRepository<Coche, Long>{

}
