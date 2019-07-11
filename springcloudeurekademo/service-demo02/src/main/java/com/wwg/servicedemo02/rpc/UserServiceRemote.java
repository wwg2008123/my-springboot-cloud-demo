package com.wwg.servicedemo02.rpc;

import com.wwg.servicedemo01.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@FeignClient(value = "service-demo01", fallback = UserServiceRemoteHystrix.class)
public interface UserServiceRemote {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getUser(@RequestParam("param") String param);

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(@RequestParam("param") String param);


    @RequestMapping(value = "adduser1", method = RequestMethod.POST)
    public String addUser1(@RequestBody User user);


    @RequestMapping(value = "/testapi", method = RequestMethod.GET)
    public Map<String, Object> testapi(@RequestParam("userId") String userId, @RequestParam("name") String name, @RequestParam("age") String age);


    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void sendMessage(@RequestParam("message") String message);


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam("userNo") String userNo, @RequestParam("password") String password);

}
