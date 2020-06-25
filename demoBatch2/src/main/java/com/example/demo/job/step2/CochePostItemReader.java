package com.example.demo.job.step2;

import java.util.Iterator;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.CochePost;
import com.example.demo.repository.CochePostRepository;

public class CochePostItemReader implements ItemReader<CochePost> {
	
	@Autowired
	private CochePostRepository repository; // Hay que importar un objeto de esta clase que implementa a JpaRepository y permite utilizar sus métodos
	
	private Iterator<CochePost> iterator; // Iterador para ir recorriendo todos los objetos que devuelva la BD
	
	/**
	 * Antes de comenzar a ejecutarse el step se indica que recupere todos los objetos de la BD que sean "opel" y devuelve el iterador
	 */
	@BeforeStep
	public void before(StepExecution stepExecution) {
		iterator = repository.findByMarca("opel").iterator(); // La query de fondo se encuentra en CochePostRepository
	}

	/**
	 * Este método va devolviendo cada uno de los objetos que se han almacenado en el iterador y cuando no quedan más y debe finalizar devuelve null
	 */
	@Override
	public CochePost read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		 if (iterator != null && iterator.hasNext()) {
	            return iterator.next();
	        } else {
	            return null;
	        }
	}

}
