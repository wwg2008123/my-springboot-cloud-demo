/*
package com.wwg.gatewayzuul.security;

import jwt.JwtAuthenticationFilter;
import jwt.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableConfigurationProperties
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Value("${jwt.expirationTime}")
    public long expirationTime;
    @Value("${jwt.secret}")
    public String secret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // 允许对于指定路径 或 网站静态资源的无授权访问
                .antMatchers("/", "/httpstest", "/prometheus","/health").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // ReST is stateless, no sessions // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // We filter the api/login requests  // 添加JWT filter
                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager(), secret, expirationTime),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JwtAuthenticationFilter(secret), UsernamePasswordAuthenticationFilter.class)
                // return 403 when not authenticated
                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }
}
*/
