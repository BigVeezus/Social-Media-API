package com.springboot.blogAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.springboot.blogAPI.model")
public class BlogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}

}
