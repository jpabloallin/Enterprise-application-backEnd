package com.enterprise.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class EnterpriseApplicationBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseApplicationBackEndApplication.class, args);
	}

}
