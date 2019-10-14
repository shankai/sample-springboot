package io.github.shankai.springboot.jetty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@RestController
@Log4j2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ServletWebServerApplicationContext webServer;
	
	@RequestMapping("/")
	public String embedded() {
		log.info(webServer.getId());
		log.info(webServer.getApplicationName());
		return "embedded server";
	}

}
