package com.wwg.servicedemo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceDemo02Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemo02Application.class, args);
        System.out.println("服务提供=======2222");
    }
}
