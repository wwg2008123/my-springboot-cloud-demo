package com.wwg.serviceconsumerdemo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//启用服务注册与发现

@EnableEurekaClient
@EnableFeignClients     //启用feign进行远程调用
@SpringBootApplication
public class ServiceConsumerDemo01Application {

    public static void main(String[] args) {

        SpringApplication.run(ServiceConsumerDemo01Application.class, args);
        System.out.println("消费服务启动成功====");
    }
}
