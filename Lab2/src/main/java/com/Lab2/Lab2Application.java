package com.Lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.Lab2")
@EnableJpaRepositories(basePackages = "com.Lab2")
@EntityScan(basePackages = "com.Lab2")
public class Lab2Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
    }
}
