package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import com.example.demo.domain.CochePost;

public class CochePostItemReaderListener implements ItemReadListener<CochePost>{

	 private static final Logger LOGGER = LoggerFactory.getLogger(CochePostItemReaderListener.class);

	    @Override
	    public void beforeRead() {
	        LOGGER.info("Antes de leer");
	    }

	    @Override
	    public void afterRead(CochePost coche) {
	        LOGGER.info("Después de leer: " + coche.toString());
	    }

	    @Override
	    public void onReadError(Exception e) {
	        LOGGER.info("Error al leer");
	    }

}
