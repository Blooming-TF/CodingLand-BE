package com.codingland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.codingland")
public class CodingLandApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingLandApplication.class, args);
    }
}
