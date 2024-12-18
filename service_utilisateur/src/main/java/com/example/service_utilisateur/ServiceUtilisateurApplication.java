package com.example.service_utilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.service_utilisateur.*")

@EnableDiscoveryClient
@EntityScan("org.example.entites")

public class ServiceUtilisateurApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUtilisateurApplication.class, args);
    }

}
