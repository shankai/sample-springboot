package io.github.shankai.springboot.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class DemoApplication {

	public static void main(String[] args) {
		log.info("this is a info message");
		log.warn("this is a warn message");
		log.error("this is a error message");

		SpringApplication.run(DemoApplication.class, args);
	}

}
