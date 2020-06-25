package com.example.demo.job;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.demo.domain.Coche;

public class CocheItemReader implements ItemStreamReader<Coche> {
	
	@Autowired
	@Qualifier("flatReader")
	FlatFileItemReader<Coche> reader; // Objeto que contiene especificados los parametros para la lectura

	@Override
	public Coche read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		reader.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		reader.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		reader.close();
	}

}
