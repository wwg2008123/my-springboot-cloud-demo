package com.wwg.servicedemo01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class ServiceDemo01ApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        try {
            System.out.println(1);
            throw new Exception("exception test");
        } catch (Exception e) {
            System.out.println(2);
            e.printStackTrace();
        }
        System.out.println(3);
    }


}
