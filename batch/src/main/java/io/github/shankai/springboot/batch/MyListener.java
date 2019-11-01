package io.github.shankai.springboot.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

/**
 * MyListener
 */
@Log4j2
@Configuration
public class MyListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("My Job Execution COMPLETED");
            log.info("Writer Result: {}", System.getProperty("SpringBatch"));
        }
        super.afterJob(jobExecution);
    }
}