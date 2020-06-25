package com.example.demo.job;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.domain.Coche;
import com.example.demo.domain.CochePost;

public class CocheItemProcessor implements ItemProcessor<Coche, CochePost>{
	
	/**
	 * Este método se encarga del procesamiento de los datos de entrada para generar una salida
	 */
	@Override
	public CochePost process(Coche item) throws Exception {
		
		float ratio = item.marca.length() / item.modelo.length();
		
		return new CochePost(item, ratio);
		
	}

}
