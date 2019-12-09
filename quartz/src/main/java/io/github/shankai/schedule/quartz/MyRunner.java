package io.github.shankai.schedule.quartz;

import javax.annotation.PreDestroy;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MyRunner implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyRunner.class, args);
    }

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting...");
        scheduler.start();
    }

    @PreDestroy
    public void exit() {
        log.info("Stoping....");
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}