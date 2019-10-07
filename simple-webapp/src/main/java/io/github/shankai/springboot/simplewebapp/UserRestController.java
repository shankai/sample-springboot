package io.github.shankai.springboot.simplewebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

/**
 * UserRestController
 */
@RestController
@Log4j2
public class UserRestController {

    @Autowired
    UserService userService;

    @RequestMapping("/rest/users")
    public ResponseEntity<Object> getUsers() {
        Iterable<User> users = userService.getUsers();
        log.info(">>>> rest controller, get users result: {}", users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/user", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user) {
        log.info(">>>> rest controller, create user result: {}", user);
        return userService.createUser(user);
    }

}