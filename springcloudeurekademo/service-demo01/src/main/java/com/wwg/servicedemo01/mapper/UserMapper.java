package com.wwg.servicedemo01.mapper;

import com.wwg.servicedemo01.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


public interface UserMapper {


    /**
     * 查询用户
     * @return
     */
    User getUser(Map<String, Object> map);

    /**
     * 新增用户
     * @param map
     * @return
     */
    int addUser(Map<String,Object> map);
}
