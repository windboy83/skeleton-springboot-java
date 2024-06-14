package com.sample.general.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sample")
public class GeneralApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneralApiApplication.class, args);
    }
}
