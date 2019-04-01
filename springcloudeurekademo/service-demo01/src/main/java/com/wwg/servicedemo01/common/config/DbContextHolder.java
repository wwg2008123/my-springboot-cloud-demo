package com.wwg.servicedemo01.common.config;

import com.wwg.servicedemo01.common.enums.DataSourceEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbContextHolder {

    private static Logger log = LoggerFactory.getLogger(DbContextHolder.class);
    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal(){
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read(){
        local.set(DataSourceEnum.read.getType());
        log.info("数据库切换到读库...");
    }

    /**
     * 写只有一个库
     */
    public static void write(){
        local.set(DataSourceEnum.write.getType());
        log.info("数据库切换到写库...");
    }

    public static String getJdbcType() {
        return local.get();
    }


}
