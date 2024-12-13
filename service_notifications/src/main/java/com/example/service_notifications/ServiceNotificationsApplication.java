package com.example.service_notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceNotificationsApplication.class, args);
	}

}
