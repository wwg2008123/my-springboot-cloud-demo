package com.wwg.servicedemo01.service;

import com.alibaba.fastjson.JSONObject;
import com.wwg.servicedemo01.common.utils.BaseResult;
import com.wwg.servicedemo01.model.User;

import java.util.Map;

public interface UserService {
    /**
     * 查询用户
     *
     * @return
     */
    BaseResult getUser(String param);

    /**
     * 新增用户
     *
     * @param param
     * @return
     */
    BaseResult addUser(String param);

    User getUserByLoginName(String loginName);

    Map<String, Object> getLoginUserAndMenuInfo(User user);

    Map<String, Object> checkMobileAndPasswd(JSONObject requestJson) throws Exception;
}
