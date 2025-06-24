package com.smartcity.parking.PK4U;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Pk4UApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pk4UApplication.class, args);
	}

}
