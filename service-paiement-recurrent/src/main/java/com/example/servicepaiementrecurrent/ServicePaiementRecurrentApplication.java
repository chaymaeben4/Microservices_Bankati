package com.example.servicepaiementrecurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(basePackages = "org.example.entites")
@EnableFeignClients

public class ServicePaiementRecurrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePaiementRecurrentApplication.class, args);
    }

}
