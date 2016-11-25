package com.oneclouder.pidm.logs.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by clouder on 16-10-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class LogTest {

    @Test
    public void testException(){
        try {
            int i = 1/0;
        }catch (Exception e){
            e.printStackTrace();
            String str = "";
            for (StackTraceElement element:e.getStackTrace()){
                str += element+"\n";
            }
            System.out.println("str = " + str);
        }

    }

}
