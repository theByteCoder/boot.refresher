package com.springboot.refresher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.springboot.refresher"})
public class RefresherApplication {

    public static void main(String[] args) {
        SpringApplication.run(RefresherApplication.class, args);
    }

}
