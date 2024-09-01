package com.jaspereap.simple.discovery.SimpleDiscoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SimpleDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleDiscoveryServiceApplication.class, args);
	}

}
