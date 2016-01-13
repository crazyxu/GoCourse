package com.katoa.util;

import java.util.Calendar;

/**
 * Created by 徐灿 on 2015/4/12
 * Contact 384454501@qq.com
 * Introduce 处理一些不参与具体业务逻辑的操作
 */
public class Tools {
    //获取当前周次,从0开始
    public static int getCurWeeks(){
        //暂时处理
        return 0;
    }

    //获取当前星期数,从星期一（0表示）开始
    public static int getCurDays(){
        Calendar calendar=Calendar.getInstance();
        int i=calendar.get(Calendar.DAY_OF_WEEK);
        //系统默认从星期天为第一天（i为0），作此调整满足使用习惯
        if(i==1)
            return 6;
        return i-2;
    }
    //工具周次和星期数获取日期
    public static String getDataByWeeksAndDays(int weeks,int days){
        //暂且如此处理
        return "11-11";
    }

}
