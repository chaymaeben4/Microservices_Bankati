package com.example.service_portefeuilles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@EntityScan(basePackages = {"org.example.entites","com.example.service_portefeuilles.model"})
public class ServicePortefeuillesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePortefeuillesApplication.class, args);
    }
}
