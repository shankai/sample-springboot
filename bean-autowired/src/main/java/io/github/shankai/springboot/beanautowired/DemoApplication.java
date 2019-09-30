package io.github.shankai.springboot.beanautowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired()@Qualifier("user")
	private User user;

	@Autowired()@Qualifier("externalUser")
	User externalUser;

	/**
	 * @return the user
	 */
	@Bean("user")
	public User getUser() {
		log.info("execute");
		User u = new User("1", "autowired1", 10);
		log.info(u);
		return u;
	}

	@RequestMapping("/")
	public ResponseEntity<User> getUserInfo() {
		return new ResponseEntity<>(this.user, HttpStatus.OK);
	}

	@RequestMapping("/external")
	public ResponseEntity<User> getExternalUserInfo() {
		return new ResponseEntity<>(this.externalUser, HttpStatus.OK);
	}

}
