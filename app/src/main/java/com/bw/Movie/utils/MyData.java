package com.bw.Movie.utils;

import com.bw.Movie.bean.DataBean;

import java.util.Calendar;

/**
 * Created by BoyLucky on 2018/8/23.
 */

public class MyData {
    public DataBean getMyData(){
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        int year = calendar.get(Calendar.YEAR);
//月
        int month = calendar.get(Calendar.MONTH)+1;
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//获取系统时间
//小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//分钟
        int minute = calendar.get(Calendar.MINUTE);
//秒
        int second = calendar.get(Calendar.SECOND);
        DataBean dataBean = new DataBean();
        dataBean.month = month;
        dataBean.day = day;

        return dataBean;
    }
//毫秒转小时
    public static long ms2HMS(long duration){
        String time = "" ;
        long minute = duration / 3600000 ;
//        long seconds = duration % 60000 ;
//        long second = Math.round((float)seconds/1000) ;
//        if( minute < 10 ){
//            time += "0" ;
//        }
//        time += minute+":" ;
//        if( second < 10 ){
//            time += "0" ;
//        }
//        time += second ;
        return minute;
    }

}
