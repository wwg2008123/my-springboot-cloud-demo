package com.wwg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${qx.hello}")
    private String gitConfigName;

    @GetMapping("config/hello")
    public String getGitHubConfigServer(){
        return gitConfigName;
    }



}
