package com.wwg.servicedemo01.shiro;

import com.wwg.servicedemo01.shiro.cache.RedisCacheManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    /*   */

    /**
     * redis缓存方案
     *
     * @return
     */
    @Bean
    public CacheManager shiroRedisCacheManager() {
        return new RedisCacheManager();
    }

    /**
     * redis缓存方案
     *
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
        // 根据情况使用缓存器
        realm.setCacheManager(shiroRedisCacheManager());
        return realm;
    }

    /**
     * 安全管理配置
     *
     * @return
     */
    @Bean
    public SecurityManager defalutWebSecurityManager() {

        // DefaultSecurityManager defaultSecurityManager = new  DefaultSecurityManager();
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //使用自己的realm
        securityManager.setRealm(shiroRealm());

        // 注意这里必须配置securityManager
        SecurityUtils.setSecurityManager(securityManager);
        // 根据情况选择缓存器
        securityManager.setCacheManager(shiroRedisCacheManager());
        return securityManager;
    }


    /**
     * 配置shiro的拦截器链工厂,默认会拦截所有请求，并且不可配置
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new ShiroJwtFilter());
        filterFactoryBean.setFilters(filterMap);
        // 配置安全管理(必须)
        filterFactoryBean.setSecurityManager(defalutWebSecurityManager());
        // 配置登陆的地址
        filterFactoryBean.setLoginUrl("/user/login");// 未登录时候跳转URL,如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
   /*       filterFactoryBean.setSuccessUrl("/welcome.do");// 成功后欢迎页面
        filterFactoryBean.setUnauthorizedUrl("/403.do");// 未认证页面*/
        // 配置拦截地址和拦截器  // 必须使用LinkedHashMap,因为拦截有先后顺序
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/userNoLogin", "anon"); // 未登录跳转页面不设权限认证
        filterChainDefinitionMap.put("/login", "anon");  // 登录接口不设置权限认真
        filterChainDefinitionMap.put("/logout", "anon"); // 登出不需要认证
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/static", "anon");

        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");

        // 以下配置同样可以通过注解
        // @RequiresPermissions("user:edit")来配置访问权限和角色注解@RequiresRoles(value={"ROLE_USER"})方式定义
        // 权限配置示例,这里的配置理论来自数据库查询
    /*    filterChainDefinitionMap.put("/user/**", "roles[ROLE_USER],perms[query]");// /user/下面的需要ROLE_USER角色或者query权限才能访问
        filterChainDefinitionMap.put("/admin/**", "perms[ROLE_ADMIN]");// /admin/下面的所有需要ROLE_ADMIN的角色才能访问
*/
        // filterChainDefinitionMap.put("/user/**", "roles[admin] ");

        // 剩下的其他资源地址全部需要用户认证后才能访问
        // 所有请求通过我们自己的JWT Filter
        filterChainDefinitionMap.put("/**", "jwt");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 全部配置
        // anon org.apache.shiro.web.filter.authc.AnonymousFilter 匿名访问
        //
        // authc org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        // 需要登录,不需要权限和角色可访问
        //
        // authcBasic
        // org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
        //
        // perms
        // org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
        // 需要给定的权限值才能访问
        //
        // port org.apache.shiro.web.filter.authz.PortFilter
        //
        // rest org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
        //
        // roles org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
        // 需要给定的角色才能访问
        //
        // ssl org.apache.shiro.web.filter.authz.SslFilter
        //
        // user org.apache.shiro.web.filter.authc.UserFilter
        //
        // logout org.apache.shiro.web.filter.authc.LogoutFilter

        return filterFactoryBean;
    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defalutWebSecurityManager());
        return advisor;
    }
}
