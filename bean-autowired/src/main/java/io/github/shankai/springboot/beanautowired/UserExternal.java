package io.github.shankai.springboot.beanautowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UserExternal
 */
@Configuration
public class UserExternal {

    @Bean("externalUser")
    public User s() {
        return new User("2", "external-user-2", 222);
    }
}