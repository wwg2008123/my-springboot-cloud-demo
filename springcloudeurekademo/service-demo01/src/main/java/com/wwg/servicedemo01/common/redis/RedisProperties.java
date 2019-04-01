package com.wwg.servicedemo01.common.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisProperties {
    private int maxIdle;
    private int minIdle;
    private int timeout;
    private int maxTotal;
    private int maxWait;

    private List<String> intanceUrlList;
}
