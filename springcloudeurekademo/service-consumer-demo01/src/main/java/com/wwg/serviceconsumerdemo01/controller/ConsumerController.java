package com.wwg.serviceconsumerdemo01.controller;

import com.wwg.serviceconsumerdemo01.service.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class ConsumerController {
    @Autowired
    HelloRemote helloRemote;

    /*@RequestMapping("/consumerHello")
    public String index(@RequestParam("name")String name){
        return helloRemote.sayHello(name);
    }*/

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

    @RequestMapping("/info")
    public String info() {
        return helloRemote.info();
    }

}
