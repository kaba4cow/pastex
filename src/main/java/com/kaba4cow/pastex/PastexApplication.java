package com.kaba4cow.pastex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PastexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PastexApplication.class, args);
	}

}
