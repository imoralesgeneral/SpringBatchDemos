package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

import com.example.demo.domain.CochePost;
import com.example.demo.domain.CochePostCSV;

public class CochePostItemProcessListener implements ItemProcessListener<CochePost, CochePostCSV>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CochePostItemProcessListener.class);

    @Override
    public void beforeProcess(CochePost coche) {
        LOGGER.info("Antes de procesar");
    }

    @Override
    public void afterProcess(CochePost coche, CochePostCSV cochePost) {
        LOGGER.info("Después de procesar: " + coche + " ---> " + cochePost);
    }

    @Override
    public void onProcessError(CochePost coche, Exception e) {
        LOGGER.info("Error al procesar: " + coche);
    }

}
