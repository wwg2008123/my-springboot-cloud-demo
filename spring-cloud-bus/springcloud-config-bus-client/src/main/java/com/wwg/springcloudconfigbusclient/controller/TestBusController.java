package com.wwg.springcloudconfigbusclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestBusController {
    @Value("${qx.hello}")
    private String gitConfigName;

    @GetMapping("/git/hello")
    public String getGitHubConfigServer(){
        return gitConfigName;
    }
}
