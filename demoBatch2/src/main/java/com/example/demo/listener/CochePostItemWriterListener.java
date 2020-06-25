package com.example.demo.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import com.example.demo.domain.CochePostCSV;

public class CochePostItemWriterListener implements ItemWriteListener<CochePostCSV> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CochePostItemWriterListener.class);

    @Override
    public void beforeWrite(List<? extends CochePostCSV> list) {
        LOGGER.info("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends CochePostCSV> list) {
        for (CochePostCSV cochePost : list) {
            LOGGER.info("afterWrite :" + cochePost.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends CochePostCSV> list) {
        LOGGER.info("onWriteError");
    }
	
}
