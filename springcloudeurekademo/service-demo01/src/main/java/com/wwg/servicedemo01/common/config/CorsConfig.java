package com.wwg.servicedemo01.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 处理跨域问题
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //1允许服务端访问
        corsConfiguration.addAllowedOrigin("*");
        //2允许任何头
        corsConfiguration.addAllowedHeader("*");
        //3允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        //4 允许withCredentials报文头
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("**",buildConfig());
        return new CorsFilter(urlBasedCorsConfigurationSource) ;
    }
}
