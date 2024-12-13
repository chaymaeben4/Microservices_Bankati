package com.example.service_paiement_multidevises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicePaiementMultidevisesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePaiementMultidevisesApplication.class, args);
    }

}
