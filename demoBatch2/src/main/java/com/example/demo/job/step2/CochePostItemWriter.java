package com.example.demo.job.step2;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.demo.domain.CochePostCSV;

public class CochePostItemWriter implements ItemStreamWriter<CochePostCSV>{
	
	@Autowired
	@Qualifier("writerCSV")
	FlatFileItemWriter<CochePostCSV> itemWriter; // Objeto que contiene especificados los parametros para la escritura

	@Override
	public void write(List<? extends CochePostCSV> items) throws Exception {
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
