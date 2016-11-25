package com.oneclouder.pidm.survey.test;

import java.util.Date;

/**
 * Created by zheng.
 */
public class StatisQuartz{

    int count = 1;
    public void execute(){
        System.out.println("测试quartz时间"+count+++"："+new Date());
    }
}
