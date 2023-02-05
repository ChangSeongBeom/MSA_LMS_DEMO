package com.example.registerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RegisterController {

    @GetMapping("/welcome")
    public String welcomeTest(){
        return " register welcome";
    }
}
