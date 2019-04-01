package com.wwg.servicedemo01.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wwg.servicedemo01.common.annotation.ReadDSAnno;
import com.wwg.servicedemo01.common.annotation.WriteDSAnno;
import com.wwg.servicedemo01.common.utils.BaseResult;
import com.wwg.servicedemo01.common.utils.JsonUtil;
import com.wwg.servicedemo01.mapper.UserMapper;
import com.wwg.servicedemo01.model.Menu;
import com.wwg.servicedemo01.model.Role;
import com.wwg.servicedemo01.model.User;
import com.wwg.servicedemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.JWTUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @ReadDSAnno
    @Override
    public BaseResult getUser(String param) {
        BaseResult baseResult = new BaseResult();
        JSONObject json = JsonUtil.validateParam(baseResult,param,"");
        if(!baseResult.code.equals("1")){
            return baseResult;
        }
        Map<String,Object> map = json;

        User user = userMapper.getUser(map);
        baseResult.setData(user);
        return baseResult;
    }
    @WriteDSAnno
    @Override
    public BaseResult addUser(String param) {
        BaseResult baseResult = new BaseResult();
        JSONObject json = JsonUtil.validateParam(baseResult,param,"");
        if(!baseResult.code.equals("1")){
            return baseResult;
        }
        Map<String,Object> map = new HashMap();
        String name = json.getString("name");
        int age = json.getInteger("age");
        map.put("name",name);
        map.put("age",age);
        int rows = userMapper.addUser(map);
        baseResult.setData(rows);
        return baseResult;
    }
   public Map<String,Object> checkMobileAndPasswd(JSONObject requestJson)throws Exception{
        String userNo = requestJson.getString("userNo");
        String password = requestJson.getString("password");
        String token = JWTUtil.sign(userNo,password);
        //自定User数据
        User user = new User();
        user.setUserNo(userNo);
        user.setPassword(password);
        user.setName("wwg");
        user.setId(1);
        user.setRoles(null);
        user.setToken(token);
        return getLoginUserAndMenuInfo(user);
   }
    public Map<String,Object> getLoginUserAndMenuInfo(User user){
        Map<String,Object> result = new HashMap<>();
        //自定义角色数据
        Role role = new Role();
        role.setRoleCode("001");
        role.setRoleName("admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        List<Menu> menus = new ArrayList<>();
        Menu menu =  new Menu();
        menu.setId(1);
        menu.setType(0);
        menu.setMenuName("用户信息");
        menu.setMenuCode("user:info");
        menu.setUrl("/user/info");
        menus.add(menu);
        result.put("user",user);
        result.put("roleList",roles);
        result.put("menuList",menus);
        return result;
    }

    @Override
    public User getUserByLoginName(String loginName) {
        User user = new User();
        user.setUserNo(loginName);
        user.setPassword("123456");

        return user;
    }


}
