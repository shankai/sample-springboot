package io.github.shankai.springboot.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Value("${server.port}")
	int port;

	@Value("${prop.not.exist:true}")
	String notExist;

	@RequestMapping("/")
	public String props() {
		return "Hellp SpringBoot Props. Server Port:" + port + ", prop.not.exist:" + notExist;
	}

}
