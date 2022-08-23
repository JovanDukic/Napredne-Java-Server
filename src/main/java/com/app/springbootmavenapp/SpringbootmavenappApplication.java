package com.app.springbootmavenapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootmavenappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmavenappApplication.class, args);
	}

}
