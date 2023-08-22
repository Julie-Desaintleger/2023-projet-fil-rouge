package com.inetum.AppBibliotheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppBibliothequeApplication {

	
	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "init");
		SpringApplication.run(AppBibliothequeApplication.class, args);
		System.err.println("http://localhost:8080/AppBibliotheque");
	}

}
