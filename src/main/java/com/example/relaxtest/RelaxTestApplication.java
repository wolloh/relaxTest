package com.example.relaxtest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class RelaxTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelaxTestApplication.class, args);
    }

}
