package com.aavn.workshop.foodyclone.configuration.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class FoodyCloneConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodyCloneConfigurationServerApplication.class, args);
	}

}
