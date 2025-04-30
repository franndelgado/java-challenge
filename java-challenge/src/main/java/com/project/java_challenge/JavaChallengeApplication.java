package com.project.java_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JavaChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaChallengeApplication.class, args);
	}

}
