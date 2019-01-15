package com.test.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Springboot14TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot14TaskApplication.class, args);
	}

}

