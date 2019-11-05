package io.github.shankai.springboot.kafkasample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSampleApplication.class, args);
	}

	@Autowired
	KafkaProduce produce;

	@Override
	public void run(String... strings) throws Exception {
		produce.send("Spring Kafka Producer and Consumer Example sssss");
	}

}
