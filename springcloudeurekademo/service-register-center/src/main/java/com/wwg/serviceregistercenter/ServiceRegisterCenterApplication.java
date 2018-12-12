package com.wwg.serviceregistercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@EnableEurekaServer
@SpringBootApplication
public class ServiceRegisterCenterApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceRegisterCenterApplication.class, args);

   /*     List<Map<String,Object>> _list = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("name","zhangsan");
        map1.put("num","10");
        _list.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","lisi");
        map2.put("num","20");
        _list.add(map2);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("name","wangwu");
        map3.put("num","30");
        _list.add(map3);

        _list.stream().filter(itemMap->{
            if(itemMap.get("name").equals("lisi")){

                return true;
            }else {

                return false;
            }
        }).forEach(map-> {System.out.println(map);});

 */
        /*String da = "20181201";
        try {
            *//*SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date date = format.parse(da);*//*
            String nDt = new ServiceRegisterCenterApplication().getSpecifiedDateBefore(da);

            System.out.println(nDt);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

   /*     List<String> _list = new ArrayList<>();
        _list.add("2018-09-12");
        _list.add("2018-10-23");
        _list.add("2018-03-33");
        _list.add("2018-11-10");
        _list.add("2018-10-22");
        _list.add("2018-02-10");
        _list.add("2018-01-01");
        _list.add("2018-09-09");

        String maxItem = Collections.max(_list);
        System.out.println(maxItem);
*/
        System.out.println("注册中心服务启动！");
    }
    private String getSpecifiedDateBefore(String specifiedDate){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse(specifiedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day-1);
        String dayBefore = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        return dayBefore;
    }

}
