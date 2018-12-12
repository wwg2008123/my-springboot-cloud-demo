package com.wwg.servicedemo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.math.BigDecimal;

@SpringBootApplication
@EnableEurekaClient
public class ServiceDemo01Application {

    public static void main(String[] args) {

        SpringApplication.run(ServiceDemo01Application.class, args);
        System.out.println("服务提供=====111");

    }
}
