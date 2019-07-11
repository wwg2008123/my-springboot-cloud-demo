package com.wwg.servicedemo01.controller;

import cache.RedisService;
import cache.RedisServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wwg.servicedemo01.common.enums.StatusCodeEnum;
//import com.wwg.servicedemo01.common.redis.RedisImplServer;
import com.wwg.servicedemo01.common.utils.BaseResult;
import com.wwg.servicedemo01.model.User;
import com.wwg.servicedemo01.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Api("swaggerDemoController相关的api")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

/*    @Resource
    private RedisImplServer redisService;*/

/*
    @Autowired
    RabbitTemplate rabbitTemplate;
*/

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getUser(String param) {
        BaseResult baseResult = userService.getUser(param);
        String strJson = JSON.toJSONString(baseResult);
        return strJson;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(String param) {
        BaseResult baseResult = userService.addUser(param);
        String strJson = JSON.toJSONString(baseResult);
        return strJson;
    }

    @ApiOperation(value = "User add", notes = "新增用户数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User")
    })
    @RequestMapping(value = "adduser1", method = RequestMethod.POST)
    public String addUser1(@RequestBody User user) {
        BaseResult baseResult = new BaseResult();
        if (user == null) {
            baseResult.setMessage("参数为空或无效");
            baseResult.setStatus(StatusCodeEnum.ERROR_NONE);
            return JSON.toJSONString(baseResult);
        }
        String param = JSON.toJSONString(user);
        baseResult = userService.addUser(param);
        return JSON.toJSONString(baseResult);
    }

    @ApiOperation(value = "swagger api 测试", notes = "api 接口测试", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "String", paramType = "query", defaultValue = "001"),
            @ApiImplicitParam(name = "name", value = "用户ID", required = false, dataType = "String", paramType = "query", defaultValue = "wwg"),
            @ApiImplicitParam(name = "age", value = "age", required = false, dataType = "String", paramType = "query", defaultValue = "30")
    })
    @RequestMapping(value = "/testapi", method = RequestMethod.GET)
    public Map<String, Object> testapi(String userId, String name, String age) {
        String userKey = userId;
        Map<String, Object> map = new HashMap<>();
   /*     String data = redisService.get(userKey);
        if (data == "" || data == null) {
            map.put("userId", userId);
            map.put("name", name);
            map.put("age", age);
            User u1 = new User();
            u1.setId(1);
            u1.setName("test");
            u1.setAge(100);
            map.put("inUser", u1);

            redisService.set(userKey, JSON.toJSONString(map));
        } else {
            map = JSON.parseObject(data, HashMap.class);
        }*/

        return map;
    }

    @ApiOperation(value = "RabbitMQ 发送消息测试", notes = "RabbitMQ 发送消息测试", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "message", required = false, dataType = "String", paramType = "query", defaultValue = "send message test"),
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void sendMessage(String message) {
        if (message == null || message == "") {
            message = "message send test" + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        }
        System.out.println("send message :" + message);
        //       rabbitTemplate.convertAndSend("DirectExchange", "direct.message", message);
    }

    @ApiOperation(value = "login", notes = "Login 登录测试", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "userNo", required = true, dataType = "String", paramType = "query", defaultValue = "wwg"),
            @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "String", paramType = "query", defaultValue = "123456")
    })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String userNo, String password) {
        BaseResult baseResult = new BaseResult();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userNo", userNo);
        jsonObject.put("password", password);
        User user = new User();
        try {
            Map<String, Object> map = userService.checkMobileAndPasswd(jsonObject);
            baseResult.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("controller action test");
        return JSON.toJSONString(baseResult);
    }

}
