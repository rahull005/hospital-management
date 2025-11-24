package com.hospitalManagement.Docterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DocterserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocterserviceApplication.class, args);
	}

}
