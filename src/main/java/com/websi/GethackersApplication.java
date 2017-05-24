package com.websi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class GethackersApplication {

	public static void main(String[] args) {
		SpringApplication.run(GethackersApplication.class, args);
	}
}
