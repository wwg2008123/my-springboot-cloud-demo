package com.wwg.servicedemo02.controller;

import com.wwg.servicedemo02.rpc.UserServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RequestMapping("/service2")
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private UserServiceRemote userServiceRemote;

    @RequestMapping("/info")
    public String info() {
        return "hello xxx,this is service-demo02 message";
    }

    /***
     * 服务提供者
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return "hello " + name + ",this is service-demo02 message";
    }

    @RequestMapping("/user")
    public String userInfo(String userId) {
        return restTemplate.getForObject("http://localhost:9001/hello/info/", String.class);
    }
}