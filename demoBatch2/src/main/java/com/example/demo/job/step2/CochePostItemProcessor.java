package com.example.demo.job.step2;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.domain.CochePost;
import com.example.demo.domain.CochePostCSV;

public class CochePostItemProcessor implements ItemProcessor<CochePost, CochePostCSV>{
	
	/**
	 * Este método se encarga del procesamiento de los datos de entrada para generar una salida
	 */
	@Override
	public CochePostCSV process(CochePost item) throws Exception {
		
		boolean ratioMayor = isMayorQueUno(item.getRatio());
		
		return new CochePostCSV(item, ratioMayor);
		
	}
	
	private boolean isMayorQueUno(float ratio) {
		return (ratio > 1);
	}

}
