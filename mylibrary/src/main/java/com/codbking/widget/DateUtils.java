package com.codbking.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by codbking on 2016/12/15.
 */

class DateUtils {

    //获取小时
    public static int getHour(Date date) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.HOUR_OF_DAY);
    }

    //获取分钟
    public static int getMinute(Date date) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.MINUTE);
    }

    //获取周
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //获取周
    public static int getWeek(int year,int moth,int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,moth-1,day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //获取年
    public static int getYear(Date date) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.YEAR);
    }

    //获取月
    public static int getMoth(Date date) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.MONTH)+1;
    }

    //获取日
    public static int getDay(Date date) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.DATE);
    }

    public static Date getDate(int year, int moth, int day,int hour,int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, moth - 1, day, hour, minute);
        return calendar.getTime();
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    public static void main(String[] args) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            Date date=format.parse("2016-12-15 12");
            System.out.println(getHour(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
