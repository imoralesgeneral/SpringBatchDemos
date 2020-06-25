package com.example.demo.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import com.example.demo.domain.CochePost;

public class CocheItemWriterListener implements ItemWriteListener<CochePost> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CocheItemWriterListener.class);

    @Override
    public void beforeWrite(List<? extends CochePost> list) {
        LOGGER.info("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends CochePost> list) {
        for (CochePost cochePost : list) {
            LOGGER.info("afterWrite :" + cochePost.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends CochePost> list) {
        LOGGER.info("onWriteError");
    }
	
}
