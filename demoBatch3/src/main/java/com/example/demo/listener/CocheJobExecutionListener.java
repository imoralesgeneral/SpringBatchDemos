package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CocheJobExecutionListener implements JobExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(CocheJobExecutionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("Antes del job");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("Después del Job: " + jobExecution.getStatus());
    }

}
