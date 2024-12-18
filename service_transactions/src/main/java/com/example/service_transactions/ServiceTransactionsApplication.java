package com.example.service_transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.service_transactions.external")
@EnableJpaRepositories(basePackages = "com.example.service_transactions.repository")
public class ServiceTransactionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTransactionsApplication.class, args);
    }
}
