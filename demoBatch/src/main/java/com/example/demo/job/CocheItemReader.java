package com.example.demo.job;

import java.util.Iterator;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Coche;
import com.example.demo.repository.CocheRepository;

public class CocheItemReader implements ItemReader<Coche> {
	
	@Autowired
	private CocheRepository repository; // Hay que importar un objeto de esta clase que implementa a JpaRepository y permite utilizar sus métodos
	
	private Iterator<Coche> iterator; // Iterador para ir recorriendo todos los objetos que devuelva la BD
	
	/**
	 * Antes de comenzar a ejecutarse el step se indica que recupere todos los objetos de la BD (con findAll) y devuelve el iterador
	 */
	@BeforeStep
	public void before(StepExecution stepExecution) {
		iterator = repository.findAll().iterator();
	}

	/**
	 * Este método va devolviendo cada uno de los objetos que se han almacenado en el iterador y cuando no quedan más y debe finalizar devuelve null
	 */
	@Override
	public Coche read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		 if (iterator != null && iterator.hasNext()) {
	            return iterator.next();
	        } else {
	            return null;
	        }
	}

}
