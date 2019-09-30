package io.github.shankai.springboot.except;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/div/{m}/{n}")
	public ResponseEntity<Object> getResult(@PathVariable("m") long m, @PathVariable("n") long n) {
		return new ResponseEntity<>(m/n, HttpStatus.OK);
	}

}
