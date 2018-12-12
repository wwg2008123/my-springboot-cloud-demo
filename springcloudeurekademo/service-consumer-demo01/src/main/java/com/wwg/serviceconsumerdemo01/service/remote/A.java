package com.wwg.serviceconsumerdemo01.service.remote;

import org.springframework.stereotype.Service;

@Service
public class A implements HelloRemote {
    @Override
    public String hello(String name) {
        return null;
    }

    @Override
    public String info() {
        return null;
    }
}
