package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

import com.example.demo.domain.Coche;
import com.example.demo.domain.CochePost;

public class CocheItemProcessListener implements ItemProcessListener<Coche, CochePost>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CocheItemProcessListener.class);

    @Override
    public void beforeProcess(Coche coche) {
        LOGGER.info("Antes de procesar");
    }

    @Override
    public void afterProcess(Coche coche, CochePost cochePost) {
        LOGGER.info("Después de procesar: " + coche + " ---> " + cochePost);
    }

    @Override
    public void onProcessError(Coche coche, Exception e) {
        LOGGER.info("Error al procesar: " + coche);
    }

}
