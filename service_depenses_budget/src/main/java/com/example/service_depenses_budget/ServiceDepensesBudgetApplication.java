package com.example.service_depenses_budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDepensesBudgetApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDepensesBudgetApplication.class, args);
    }

}
