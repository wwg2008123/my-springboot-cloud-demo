package com.wwg.servicedemo03.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {


    @RequestMapping("/test/{mess}")
    public String testHello(@PathVariable(name="mess") String mess){
        String message = "Test Hello : "+mess;
        return message;
    }

}
