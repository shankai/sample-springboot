package io.github.shankai.springboot.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.extern.log4j.Log4j2;

/**
 * MyInterceptorRegistry
 */
@Component
@Log4j2
public class MyInterceptorRegistry extends WebMvcConfigurerAdapter {

    @Autowired
    private HandlerInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor);
    }
    
}