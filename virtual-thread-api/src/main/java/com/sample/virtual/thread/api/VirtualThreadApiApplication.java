package com.sample.virtual.thread.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sample")
public class VirtualThreadApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(VirtualThreadApiApplication.class, args);
    }
}
