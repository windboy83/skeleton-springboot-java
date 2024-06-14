package com.sample.webflux.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sample")
public class WebfluxApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxApiApplication.class, args);
    }
}
