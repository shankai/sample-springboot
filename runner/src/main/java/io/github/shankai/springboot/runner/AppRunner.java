package io.github.shankai.springboot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

/**
 * AppRunner
 */
@SpringBootApplication
@Log4j2
public class AppRunner implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
        log.info("=== SpringApplication.run...");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=== application.runner ");
    }
}