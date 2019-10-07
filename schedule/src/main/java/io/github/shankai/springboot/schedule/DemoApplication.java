package io.github.shankai.springboot.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableScheduling
@Log4j2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Scheduled(cron = "0,10,20,30,40,50 * * * * ?")
	public void job1() {
		log.info(">>> Cron Job executed. === {}", new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}

	@Scheduled(fixedRate = 1000)
	public void job2() {
		log.info(">>> Fixed Rate Job executed. === {}", new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}

	@Scheduled(initialDelay = 1000, fixedDelay = 3000)
	public void job3() {
		log.info(">>> Fixed Delay, Fixed Rate Job executed. === {}",
				new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}

}
