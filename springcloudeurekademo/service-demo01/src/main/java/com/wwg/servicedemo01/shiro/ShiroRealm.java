package com.wwg.servicedemo01.shiro;

import com.alibaba.druid.util.StringUtils;
import com.wwg.servicedemo01.model.Role;
import com.wwg.servicedemo01.model.User;
import com.wwg.servicedemo01.service.RoleService;
import com.wwg.servicedemo01.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import utils.ComUtil;
import utils.JWTUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 必须重写此方法，不然Shiro会报错
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 获取用户授权
     * 获取授权信息
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        log.info("#########################执行Shiro权限认证#######################");
        // 获取用户名
        String userNo = JWTUtil.getUserNo(principal.toString());
        // 判断用户名是否存在
        if (ComUtil.isEmpty(userNo)) {
            return null;
        }

        // 查询登录用户信息
        User user = userService.getUserByLoginName(userNo);
        if (user == null) {
            log.warn("用户[" + userNo + "]信息不存在");
            return null;
        }

        //创建一个授权对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //进行权限设置
        Set<String> permissions = new HashSet<>();
        if (permissions != null && !permissions.isEmpty()) {
            authorizationInfo.setStringPermissions(permissions);
        }

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        if (roles != null && !roles.isEmpty()) {
            authorizationInfo.setRoles(roles);

        }
        Set<String> permissUrls = new HashSet<>();
        permissUrls.add("/user/info");
        permissUrls.add("/user/add");
        permissUrls.add("/user/get");
        authorizationInfo.setStringPermissions(permissUrls);
        return authorizationInfo;

    }

    /**
     * 获取用户认证信息
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("#########################执行Shiro登陆认证##########################");
        //UsernamePasswordToken token = new UsernamePasswordToken();
        String token = (String) authenticationToken.getCredentials();

        //解密获得username，用于和数据库进行对比
        String userNo = JWTUtil.getUserNo(token);
        //判断token 是否有效
        if (ComUtil.isEmpty(userNo)) {
            throw new UnauthorizedException("token invalid!");
        }

        // 模拟数据库查询用户信息
        User user = userService.getUserByLoginName(userNo);
        if (user != null) {
            throw new UnauthorizedException("user not existed!");
        }
        if (!JWTUtil.verify(token, userNo, user.getPassword())) {
            throw new UnauthorizedException("UserName or Password error!");
        }

        // 创建shiro的用户认证对象
        // 注意该对象的密码将会传递至后续步骤与前面登陆的subject的密码进行比对
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(token, token, user.getUserNo());
        return authenticationInfo;


    }
}
