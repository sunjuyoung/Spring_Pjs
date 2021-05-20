package com.test.springboot02.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    @GetMapping("/register")
    public String registerForm(){

        return "movie/register";
    }
}
