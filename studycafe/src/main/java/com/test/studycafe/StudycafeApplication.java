package com.test.studycafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class StudycafeApplication {

    public static void main(String[] args) {


        SpringApplication.run(StudycafeApplication.class, args);
    }

}
