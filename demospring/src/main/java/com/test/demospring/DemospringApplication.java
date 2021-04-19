package com.test.demospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemospringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemospringApplication.class, args);
    }

}
