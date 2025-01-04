package com.example.cmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.example.entites")

public class CmiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmiApplication.class, args);
    }

}
