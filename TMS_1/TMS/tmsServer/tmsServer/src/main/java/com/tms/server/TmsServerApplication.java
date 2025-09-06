package com.tms.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TmsServerApplication {

	public static void main(String[] args) {
		System.out.println("TMS Sever Registery Started");
		SpringApplication.run(TmsServerApplication.class, args);
		System.out.println("Tms registry server is runing ");
	}

}
