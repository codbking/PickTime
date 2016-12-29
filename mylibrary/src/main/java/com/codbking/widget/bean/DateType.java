package com.codbking.widget.bean;

/**
 * Created by wulang on 2016/9/22.
 */

public enum DateType {

    TYPE_ALL("yyyy-MM-dd E hh:mm"),//年、月、日、星期、时、分
    TYPE_YMDHM("yyyy-MM-dd hh:mm"),//年、月、日、时、分
    TYPE_YMDH("yyyy-MM-dd hh"),//年、月、日、时
    TYPE_YMD("yyyy-MM-dd"),//年、月、日
    TYPE_HM("hh:mm");//时、分

    private String format;

    DateType(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
    
}
