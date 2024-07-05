package com.sample.webmvc.api.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sample")
public class WebmvcApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebmvcApiApplication.class, args);
    }
}
