package com.example.reviewservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins ="*")
public class ReviewController {

    @GetMapping("/welcome")
    public String welcomeTest(){
        return " review welcome";
    }
}
