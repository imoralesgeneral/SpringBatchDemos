package com.example.demo.job;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.CochePost;
import com.example.demo.repository.CochePostRepository;

public class CocheItemWriter implements ItemWriter<CochePost>{
	
	@Autowired
	private CochePostRepository repository; // Hay que importar un objeto de esta clase que implementa a JpaRepository y permite utilizar sus métodos

	/**
	 * Escribe cada uno de los objetos procesados en la BD
	 */
	@Override
	public void write(List<? extends CochePost> items) throws Exception {
		repository.saveAll(items);		
	}
		
}
