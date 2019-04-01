package com.wwg.servicedemo01.common.config;

import org.springframework.context.ApplicationContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnClass({EnableTransactionManagement.class})
@Import({DruidDataSourceConfig.class})
@MapperScan(basePackages = "com.wwg.servicedemo01.mapper")
public class MybatisConfig {
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    @Value("${datasource.readSize}")
    private String dataSourceSize;


    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(ApplicationContext context) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy(context));
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.wwg.servicedemo01.model");
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();

    }

    /**
     * 有多少个数据源就要配置多少个bean
     * @param context
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy(ApplicationContext context){
        int size = Integer.parseInt(dataSourceSize);
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object,Object> targetDataSource = new HashMap<>();
        DataSource writeDataSource = (DataSource) context.getBean("writeDataSource");
        List<DataSource> readDataSources = (List<DataSource>) context.getBean("readDataSources");
        for(int i=0;i<size;i++){
            targetDataSource.put(i,readDataSources.get(i));
        }
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSource);
        return proxy;
    }
}
