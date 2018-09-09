package com.hanifcarroll.CatApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class CatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatAppApplication.class, args);
	}
}
