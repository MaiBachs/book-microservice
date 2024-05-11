package com.example.Postcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PostcastApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostcastApplication.class, args);
	}

}
