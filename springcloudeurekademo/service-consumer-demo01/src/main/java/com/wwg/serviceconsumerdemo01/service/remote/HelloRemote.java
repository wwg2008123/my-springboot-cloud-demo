package com.wwg.serviceconsumerdemo01.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-demo", fallback = A.class)   //name:远程服务名，即spring.application.name配置的名称
public interface HelloRemote {
    //需要匹配服务提供者接口名称
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/info")
    public String info();
}
