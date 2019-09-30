package io.github.shankai.springboot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

/**
 * CmdRunner
 */
@SpringBootApplication
@Log4j2
public class CmdRunner implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CmdRunner.class, args);
        log.info("=== SpringApplication.run...");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("=== commandline.runner: {}", args.toString());
    }

}