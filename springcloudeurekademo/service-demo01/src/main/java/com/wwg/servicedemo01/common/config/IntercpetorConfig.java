package com.wwg.servicedemo01.common.config;

import com.wwg.servicedemo01.common.interceptor.Loginterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class IntercpetorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //*.js,*.gif,*.jpg,*.png,*.css,*.ico,
        List<String> _list = new ArrayList<>();
        _list.add("*.js");
        _list.add("*.gif");
        _list.add(".jpg");
        _list.add("*.png");
        _list.add(".css");
        _list.add("*.ico");
        _list.add("*.ttf");
        _list.add("/swagger-*/**");
        registry.addInterceptor(new Loginterceptor()).addPathPatterns("/**").excludePathPatterns(_list);


    }


}
