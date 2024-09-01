package com.jaspereap.simple.management.SimpleAccountManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/account")
public class SimpleAccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleAccountManagementApplication.class, args);
	}

	@GetMapping("/status/check")
	public String status() {
		return "Account management working";
	}

}
