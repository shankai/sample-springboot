package io.github.shankai.springboot.simplewebapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserController
 */
@Controller
public class UserController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
}