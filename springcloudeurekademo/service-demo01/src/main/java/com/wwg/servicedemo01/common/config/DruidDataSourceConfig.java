package com.wwg.servicedemo01.common.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DruidDataSourceConfig {
    private static Logger log = LoggerFactory.getLogger(DruidDataSourceConfig.class);
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    @Value("${druid.init.prefix}")
    private String prefix;
    @Value("${druid.init.allow}")
    private String allow;
    @Value("${druid.init.deny}")
    private String deny;
    @Value("${druid.init.loginUsername}")
    private String loginUsername;
    @Value("${druid.init.loginPassword}")
    private String loginPassword;
    @Value("${druid.init.resetEnable}")
    private String resetEnable;

    @Bean
    public ServletRegistrationBean druidServlet() {
        log.info("init Druid Serlet Configuration ");
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings(prefix);
        //IP 白名单
        bean.addInitParameter("allow", allow);
        //IP  黑名单（共同存在时，deny 优先于allow）
        //bean.addInitParameter("deny",deny);
        //控制台管理用户
        bean.addInitParameter("loginUsername", loginUsername);
        bean.addInitParameter("loginPassword", loginPassword);
        //是否能够重置数据 禁用 HTML页面上的 “Reset All”功能
        bean.addInitParameter("resetEnable", resetEnable);
        return bean;

    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        bean.addUrlPatterns("/*");
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico," + prefix);
        return bean;
    }

    //@Bean(name="writeDataSource",destroyMethod = "close", initMethod="init")
    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource writeDataSource() {
        log.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 有多少个从库就要配置多少个
     *
     * @return
     */
    //@Bean(name="readDataSource" ,destroyMethod = "close" ,initMethod = "init")
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource readDataSourceOne() {
        log.info("-------------------- readDataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 这里的list是多个从库的情况下为了实现简单负载均衡
     *
     * @return
     * @throws SQLException
     */
    @Bean(name = "readDataSources")
    public List<DataSource> readDataSources() throws SQLException {
        List<DataSource> dataSources = new ArrayList<>();
        dataSources.add(readDataSourceOne());
        return dataSources;
    }

}
