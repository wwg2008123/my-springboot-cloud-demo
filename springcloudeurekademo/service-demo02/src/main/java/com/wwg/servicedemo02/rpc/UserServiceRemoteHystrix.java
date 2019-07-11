package com.wwg.servicedemo02.rpc;

import com.wwg.servicedemo01.model.User;
import exception.CheckException;
import exception.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserServiceRemoteHystrix implements UserServiceRemote {
    String baseErrMsg = "Service-demo01 微服务 ";

    @Override
    public String getUser(String param) {
        return throwCircuitBreak("getUser");
    }

    @Override
    public String addUser(String param) {
        return throwCircuitBreak("addUser");
    }

    @Override
    public String addUser1(User user) {
        return throwCircuitBreak("addUser1");
    }

    @Override
    public Map<String, Object> testapi(String userId, String name, String age) {
        return throwCircuitBreak("testapi");
    }

    @Override
    public void sendMessage(String message) {
        throwCircuitBreak("sendMessage");
    }

    @Override
    public String login(String userNo, String password) {
        return throwCircuitBreak("login");
    }

    private <T> T throwCircuitBreak(String method) {
        String errMsg = baseErrMsg + ", " + method + " 方法被熔断";
        log.info(errMsg);
        throw new CheckException(this, CommonErrorCode.ERROR_CODE_CIRCUIT_BREAK, errMsg);
    }
}
