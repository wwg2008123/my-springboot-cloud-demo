PostMan以POST方式请求http://localhost:9090/bus/refresh，结果返回的是 405，后台提示
Request method 'POST' not supported
SpringCloud2.0做了重大的改动， /bus/refresh全部整合到actuador里面了，具体的问题参考这里，于是我就再pom.xml 里添加了
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
在application.properties文件中添加了
management.endpoint.bus-refresh.enabled=true
management.endpoints.web.exposure.include=bus-refresh

config-bus-client 客户端也配置成上面一样的

---------------------------------------------------------

#spring.application.name：对应{application}部分
#spring.cloud.config.name：对应配置文件名称 git-config-dev.yml
#spring.cloud.config.profile：对应{profile}部分
#spring.cloud.config.label：对应git的分支。如果配置中心使用的是本地存储，则该参数无用
#spring.cloud.config.uri：配置中心的具体地址
#spring.cloud.config.discovery.service-id：指定配置中心的service-id，便于扩展为高可用配置集群。

1