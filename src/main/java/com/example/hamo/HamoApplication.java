package com.example.hamo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class}) 
public class HamoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamoApplication.class, args);
	}

}