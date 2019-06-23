package com.example.ian.keepaccount.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String longToDoubleToString(long l) {
        double d = (double) l;
        BigDecimal b = new BigDecimal(d);
        double dResult = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(dResult);
    }

    public static String getYMTime(Date currentTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        return sdf.format(currentTime);
    }

    public static String getStringDate(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(time);
    }

    public static String getStringDate2(Date time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    public static Date strToDate(String time) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期加1天
     */
    public static Date dateAddDay(Date date){
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.set(Calendar.HOUR_OF_DAY,23);
        time.add(Calendar.MINUTE,59);
        time.add(Calendar.SECOND,59);
        time.add(Calendar.MILLISECOND,59);
        return time.getTime();

    }

    /**
     * 日期加1月
     */
    public static Date dateAddMonth(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, 1);// 日期加1个月
        return rightNow.getTime();
    }

    /**
     * 获取月初时间
     */
    public static Date getStartTime(Date dt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    /**
     * 获取当月月尾时间
     */
    public static Date getEndDate(Date curTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curTime);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH,1);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,59);
        return calendar.getTime();
    }

    /**
     * 字符串转Date
     */
    public static Date stringToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(System.currentTimeMillis());
    }

    /**
     * 日期设为当天凌晨
     */
    public static Date getStartDay(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    /**
     * 获取当前日期的年
     */
    public static int getCurYear(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

}
