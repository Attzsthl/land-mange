package com.lete.land;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.lete.land")
@EnableCaching
public class LandControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandControllerApplication.class, args);
    }

}
