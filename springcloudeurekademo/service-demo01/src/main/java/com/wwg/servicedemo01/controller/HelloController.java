package com.wwg.servicedemo01.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {
    @Value("${server.port}")
    String port;

    @GetMapping("/info")
    public String info() {
        return "hello xxx,this is service-demo01 message,端口：" + port;
    }

    /***
     * 服务提供者
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return "hello " + name + ",this is service-demo01 message";
    }
}
