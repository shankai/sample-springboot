package io.github.shankai.springboot.resttemplate;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

/**
 * RestTemplateController
 */
@RestController
@Log4j2
public class RestTemplateController {

    private final String serverPrefix = "http://localhost:8080";

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/template/get")
    public String invokeUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        String result = restTemplate.exchange(serverPrefix + "/users", HttpMethod.GET, entity, String.class).getBody();
        log.info(">>> GET result: {}", result);
        return result;
    }

    @RequestMapping("/template/get/{id}")
    public String invokeUser(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        String result = restTemplate.exchange(serverPrefix + "/user/" + id, HttpMethod.GET, entity, String.class)
                .getBody();
        log.info(">>> GET One result: {}", result);
        return result;
    }

    @RequestMapping(value = "/template/post", method = RequestMethod.POST)
    public String invokePost(@RequestBody User user) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        String result = restTemplate.exchange(serverPrefix + "/user", HttpMethod.POST, entity, String.class).getBody();
        log.info(">>> POST result: {}", result);
        return result;
    }

    @RequestMapping(value = "/template/put/{id}", method = RequestMethod.PUT)
    public String invokePut(@PathVariable("id") String id, @RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        String result = restTemplate.exchange(serverPrefix + "/user/" + id, HttpMethod.PUT, entity, String.class)
                .getBody();
        log.info(">>> PUT result: {}", result);
        return result;
    }

    @RequestMapping(value = "/template/delete/{id}", method = RequestMethod.DELETE)
    public String invokeDelete(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        String result = restTemplate.exchange(serverPrefix + "/user/" + id, HttpMethod.DELETE, entity, String.class)
                .getBody();
        log.info(">>> DELETE result: {}", result);
        return result;
    }

}