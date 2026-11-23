package com.wipro.springsecurityhandson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/")
    public String welcome() {

        return "Welcome to Spring Security";
    }
}