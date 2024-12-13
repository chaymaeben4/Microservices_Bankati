package com.example.service_cartes_virtuelles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCartesVirtuellesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCartesVirtuellesApplication.class, args);
    }

}
