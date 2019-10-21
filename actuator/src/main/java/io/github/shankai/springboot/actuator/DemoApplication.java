package io.github.shankai.springboot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String getInfo() {
		return "SpringBoot Actuator Sample.";
	}

	@GetMapping("/response")
	public ResponseEntity<Object> getEntity() {
		return new ResponseEntity<>("SpringBoot Actuator ResponseEntity.", HttpStatus.OK);
	}

}
