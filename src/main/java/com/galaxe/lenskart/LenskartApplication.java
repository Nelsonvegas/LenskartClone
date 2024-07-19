package com.galaxe.lenskart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LenskartApplication {

	public static void main(String[] args) {
		SpringApplication.run(LenskartApplication.class, args);
	}

}
