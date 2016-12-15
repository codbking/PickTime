package com.codbking.widget.bean;

/**
 * Created by codbking on 2016/9/22.
 */

public class DateBean {

    private int year;
    private int moth;
    private int day;
    private int hour;
    private int minute;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMoth() {
        return moth;
    }

    public void setMoth(int moth) {
        this.moth = moth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    private String makeZero(int m){

         if(m>9){
             return  ""+m;
         }
        if(m>0){
            return "0"+m;
        }
        return ""+m;
    }

    public String getDisplayDate(DateType type){
        String date="";
        switch(type){
            case TYPE_ALL:
                date=year+"-"+makeZero(moth)+"-"+makeZero(day)+" "+makeZero(hour)+":"+makeZero(minute);
         break;
            case TYPE_YMDHM:
                date=year+"-"+makeZero(moth)+"-"+makeZero(day)+" "+makeZero(hour)+":"+makeZero(minute);
                break;
            case TYPE_YMDH:
                date=year+"-"+makeZero(moth)+"-"+makeZero(day)+" "+makeZero(hour);
                break;
            case TYPE_YMD:
                date=year+"-"+makeZero(moth)+"-"+makeZero(day);
                break;
            case TYPE_HM:
                date=makeZero(hour)+":"+makeZero(minute);
                break;
        }
        return date;
    }


}
