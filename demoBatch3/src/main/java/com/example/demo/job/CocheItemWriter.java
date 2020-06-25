package com.example.demo.job;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.demo.domain.CochePost;

public class CocheItemWriter implements ItemStreamWriter<CochePost>{
	
	@Autowired
	@Qualifier("writerCSV")
	FlatFileItemWriter<CochePost> itemWriter;  // Objeto que contiene especificados los parametros para la escritura

	@Override
	public void write(List<? extends CochePost> items) throws Exception {
		itemWriter.write(items);		
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		itemWriter.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		itemWriter.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		itemWriter.close();
	}
		
}
