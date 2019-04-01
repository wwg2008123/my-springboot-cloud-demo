package com.wwg.servicedemo01.common.enums;

import lombok.Data;

public enum StatusCodeEnum {
    /*无错误 */
    ERROR_NONE("1", "成功"),
    MICRO_SERVICE_ERROR("", ""),

    //1开头（10*系统内部报错）
    SYSTEM_ERROR("SYS_10000", "系统内部异常"),
    PARAM_ERROR("SYS_10001", "参数异常");

    private String code;
    private String msg;

    StatusCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
