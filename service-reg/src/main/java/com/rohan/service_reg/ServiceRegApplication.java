package com.rohan.service_reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Enable Eureka Server
public class ServiceRegApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegApplication.class, args);
	}

}
