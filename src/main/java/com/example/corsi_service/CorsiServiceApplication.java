package com.example.corsi_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CorsiServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CorsiServiceApplication.class, args);
    }
}