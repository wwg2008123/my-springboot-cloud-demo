package com.wwg.servicedemo01.common.utils;

import com.wwg.servicedemo01.common.enums.StatusCodeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class BaseResult {
    public  String code="1";
    private String message;
    private Object data;

    public BaseResult setStatus(StatusCodeEnum statusCode) {
        this.setCode(statusCode.getCode());
        this.setMessage(statusCode.getMsg());
        if (!code.equals("1")) {
            log.warn(message);
        }
        return this;
    }
}
