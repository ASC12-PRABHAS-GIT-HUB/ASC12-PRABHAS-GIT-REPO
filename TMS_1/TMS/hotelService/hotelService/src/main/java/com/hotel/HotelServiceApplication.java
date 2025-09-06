package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HotelServiceApplication {

	public static void main(String[] args) {
		System.out.println("Starting");
		SpringApplication.run(HotelServiceApplication.class, args);
		System.out.println("Started");
	}

}
