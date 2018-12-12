package com.wwg.servicedemo02.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
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
}