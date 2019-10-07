package io.github.shankai.springboot.i18n;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

/**
 * MyControllerConfig
 */
@Configuration
@Log4j2
public class MyControllerConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        log.info(">>> MyControllerConfig");
        return new MyControllerConfig();
    }

    @Bean
    public LocaleResolver localeResolver() {
        log.info(">>> MyLocaleResolver");
        return new MyLocaleResolver();
    }

}