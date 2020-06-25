package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import com.example.demo.domain.Coche;

public class CocheItemReaderListener implements ItemReadListener<Coche>{

	 private static final Logger LOGGER = LoggerFactory.getLogger(CocheItemReaderListener.class);

	    @Override
	    public void beforeRead() {
	        LOGGER.info("Antes de leer");
	    }

	    @Override
	    public void afterRead(Coche coche) {
	        LOGGER.info("Después de leer: " + coche.toString());
	    }

	    @Override
	    public void onReadError(Exception e) {
	        LOGGER.info("Error al leer");
	    }

}
