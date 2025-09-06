package com.tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TourServiceApplication {

	public static void main(String[] args) {
		System.out.println("tour and travels");
		SpringApplication.run(TourServiceApplication.class, args);
		System.out.println("Lets go on our trip");
	}

}
