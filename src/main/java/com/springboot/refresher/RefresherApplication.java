package com.springboot.refresher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.springboot.refresher"})
public class RefresherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefresherApplication.class, args);
	}

}
