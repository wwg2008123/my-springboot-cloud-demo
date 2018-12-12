package com.wwg.springcloudconfigbusclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BusClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusClientApplication.class, args);
    }
}
