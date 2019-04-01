package com.wwg.serviceregistercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@EnableEurekaServer
@SpringBootApplication
public class ServiceRegisterCenterApplication extends Thread {
    volatile boolean flag = false;
    int i=0;
    @Override
    public void run(){
        while (!flag){
            i++;
        }

    }

    public static void main(String[] args) {
      /*  ServiceRegisterCenterApplication app = new ServiceRegisterCenterApplication();
        app.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        app.flag = true;
        System.out.println("stop="+app.i);*/
        SpringApplication.run(ServiceRegisterCenterApplication.class, args);

        //new ServiceRegisterCenterApplication().testDate();

        System.out.println("注册中心服务启动！");
    }

    private String getSpecifiedDateBefore(String specifiedDate) {
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
        calendar.set(Calendar.DATE, day - 1);
        String dayBefore = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        return dayBefore;
    }

/*

    private void testDate() {
        //记录需要访问数据库的时间段
        List<Map<String, String>> dbAcceList = new ArrayList<>();
        //需要查询数据库的日期集合
        List<String> dayAcceList = new ArrayList<>();

        dayAcceList.add("20170101");
        dayAcceList.add("20170102");

        dayAcceList.add("20170205");
        dayAcceList.add("20170206");
        dayAcceList.add("20170207");
        dayAcceList.add("20170208");
        dayAcceList.add("20170209");
        dayAcceList.add("20170210");
        dayAcceList.add("20170211");
        dayAcceList.add("20170212");
        dayAcceList.add("20170213");
        dayAcceList.add("20170214");
        dayAcceList.add("20170215");
        dayAcceList.add("20170216");
        dayAcceList.add("20170217");
        dayAcceList.add("20170218");
        dayAcceList.add("20170219");
        dayAcceList.add("20170220");
        dayAcceList.add("20170221");
        dayAcceList.add("20170222");
        dayAcceList.add("20170223");
        dayAcceList.add("20170224");
        dayAcceList.add("20170225");
        dayAcceList.add("20170226");
        dayAcceList.add("20170227");
        dayAcceList.add("20170228");
        dayAcceList.add("20170301");
        dayAcceList.add("20170302");
        dayAcceList.add("20170303");
        dayAcceList.add("20170304");
        dayAcceList.add("20170305");
        dayAcceList.add("20170306");
        dayAcceList.add("20170307");
        dayAcceList.add("20170308");
        dayAcceList.add("20170309");
        dayAcceList.add("20170310");
        dayAcceList.add("20170311");
        dayAcceList.add("20170312");
        dayAcceList.add("20170313");
        dayAcceList.add("20170314");

        dayAcceList.add("20170501");
        dayAcceList.add("20170603");
        dayAcceList.add("20170801");
        dayAcceList.add("20170909");
        dayAcceList.add("20170910");

        dayAcceList.add("20171110");
        dayAcceList.add("20171111");
        dayAcceList.add("20171112");
        dayAcceList.add("20171113");
        dayAcceList.add("20171114");
        dayAcceList.add("20171115");
        dayAcceList.add("20171116");
        dayAcceList.add("20171117");
        dayAcceList.add("20171118");
        dayAcceList.add("20171119");
        dayAcceList.add("20171120");
        dayAcceList.add("20171121");
        dayAcceList.add("20171122");
        dayAcceList.add("20171123");
        dayAcceList.add("20171124");
        dayAcceList.add("20171125");
        dayAcceList.add("20171126");
        dayAcceList.add("20171127");
        dayAcceList.add("20171128");
        dayAcceList.add("20171129");


        String beginDate ="20170205";
        String endDate ="20170220";
        */
/*List<String> curForList = new ArrayList<>();
        dayAcceList.stream().filter(n-> n.compareTo(beginDate) >= 0 && n.compareTo(endDate) <=0).forEach(m->{
            curForList.add(m);
        });*//*

        List<String> curForList = dayAcceList.stream().filter(n-> n.compareTo(beginDate) >= 0 && n.compareTo(endDate) <=0).collect(Collectors.toList());
        System.out.println(curForList);

        //dayAcceList 将日期集合划分成,时间段范围
 */
/*       if (dayAcceList != null && dayAcceList.size() > 0) {
            Map<String, String> dbMap = null;
            for (int i = 0; i < dayAcceList.size(); i++) {
                if (dbMap == null || (dbMap.containsKey("beginDay") && dbMap.containsKey("endDay"))) {
                    dbMap = new HashMap<>();
                    dbMap.put("beginDay", dayAcceList.get(i));
                }

                String iDay = dayAcceList.get(i);

                int j = (i + 1) >= dayAcceList.size() ? i : (i + 1);
                if(i==j ){
                    dbMap.put("endDay", iDay);
                    dbAcceList.add(dbMap);
                    break;
                }
                for (; j < dayAcceList.size(); j++) {
                    String jDay = dayAcceList.get(j);
                    //jDay 比 iDay 大多少天
                    int ds = isMoreOneDay(iDay, jDay);
                    int count = j - i;
                    if (ds == count) {

                        //判断时间段是否大于15天
                        if (ds == 15 ) {
                            dbMap.put("endDay", jDay);
                            dbAcceList.add(dbMap);
                            i = j;
                            break;
                        }
                    } else {
                        dbMap.put("endDay", dayAcceList.get(j - 1));
                        i = j - 1;
                        dbAcceList.add(dbMap);
                        break;
                    }
                }
            }
        }
        System.out.println("查询数据时间段：" + dbAcceList);
        *//*

    }

*/

    /**
     * 判断endDay是否比fromDay大一天
     *
     * @param fromDay
     * @param endDay
     * @return
     */
    private int isMoreOneDay(String fromDay, String endDay) {
        int days = 0;
        String dateStr = fromDay.substring(0, 4) + "-" + fromDay.substring(4, 6) + "-" + fromDay.substring(6, 8);
        String endDayStr = endDay.substring(0, 4) + "-" + endDay.substring(4, 6) + "-" + endDay.substring(6, 8);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format1.parse(dateStr);
            Date date2 = format2.parse(endDayStr);

            days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

}
