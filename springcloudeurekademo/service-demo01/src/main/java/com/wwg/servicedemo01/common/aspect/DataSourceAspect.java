package com.wwg.servicedemo01.common.aspect;

import com.wwg.servicedemo01.common.annotation.ReadDSAnno;
import com.wwg.servicedemo01.common.config.DbContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {
    private static Logger log = LoggerFactory.getLogger(DataSourceAspect.class);
    @Pointcut("@annotation(com.wwg.servicedemo01.common.annotation.WriteDSAnno)")
    public void writeMethod(){}

    @Pointcut("@annotation(com.wwg.servicedemo01.common.annotation.ReadDSAnno)")
    public void readMethod(){}
    @Before("writeMethod()")
    public void beforeWrite(JoinPoint point){
        DbContextHolder.write();
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        System.out.println("开始执行:"+className+"."+methodName+"()方法...");
        log.info("dataSource切换到：write");
    }
    @Before("readMethod()")
    public void beforeRead(JoinPoint point) throws ClassNotFoundException {
        //设置数据库为读数据
        DbContextHolder.read();
        /*spring AOP测试代码*/
        String currentClassName = point.getTarget().getClass().getName(); //根据切点获取当前调用的类名
        String methodName = point.getSignature().getName();//根据切点获取当前调用的类方法
        Object[] args =point.getArgs(); //根据切点获取当前类方法的参数
        System.out.println("开始执行:"+currentClassName+"."+methodName+"()方法...");
        Class reflexClassName = Class.forName(currentClassName);//根据反射获取当前调用类的实例
        Method[] methods = reflexClassName.getMethods(); //获取该实例的所有方法
        for(Method method : methods){
            if(method.getName().equals(methodName)){
                String desrciption = method.getAnnotation(ReadDSAnno.class).description();
                System.out.println("desrciption:" + desrciption);
            }
        }
        log.info("dataSource切换到：Read");
    }

}
