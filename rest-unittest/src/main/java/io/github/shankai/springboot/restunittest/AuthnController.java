package io.github.shankai.springboot.restunittest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

/**
 * AuthnController
 */
@RestController
@Log4j2
public class AuthnController {

    @PostMapping("/authn")
    public ResponseEntity<Map<String, Object>> authn(@RequestHeader("Authorization") String encodeBase64) {

        log.info("Encode Base64: {}", encodeBase64);

        Map<String, Object> result = new HashMap<String, Object>();

        if (encodeBase64 == null || "".equals(encodeBase64) || !encodeBase64.startsWith("Basic ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String encode = encodeBase64.substring("Basic ".length()).trim();
        log.info("encode: {}", encode);

        final byte[] decoded = Base64.getDecoder().decode(encode);
        final String token = new String(decoded, StandardCharsets.UTF_8);

        log.info("token: {}", token);
        final int delim = token.indexOf(":");
        if (delim < 0) {
            throw new RuntimeException("Bad format of the basic auth header");
        }

        if ("admin:pwd".equals(token)) {

            Map<String, Object> attrs = new HashMap<String, Object>();
            attrs.put("attr1", 1);
            attrs.put("attr2", 2);
            attrs.put("attr3", 3);

            result.put("@class", "org.apereo.cas.authentication.principal.SimplePrincipal");
            result.put("id", "admin");
            result.put("attributes", attrs);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}