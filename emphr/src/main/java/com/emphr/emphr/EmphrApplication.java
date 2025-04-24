package com.emphr.emphr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.emphr")  // Ensure it scans your service package
public class EmphrApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmphrApplication.class, args);
	}
}
