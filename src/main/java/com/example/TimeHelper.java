package com.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
    private static DateFormat jsFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    private static DateFormat webFormat = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm");
    private static DateFormat webFormatWithSeconds = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm:ss");

    public static String toJS(long time){
        return jsFormat.format(new Date(time));
    }

    public static String toWeb(long time){
        return webFormat.format(new Date(time));
    }

    public static String toWebWithSeconds(long time){
        return webFormatWithSeconds.format(new Date(time));
    }

    public static String toDuration(long time){
        boolean flag = time < 0;
        time = Math.abs(time / 1000 / 60);
        long minutes, hours, days;
        minutes = time % 60;
        time /= 60;
        hours = time % 24;
        time /= 24;
        days = time;
        return String.format("%s%S:%S:%S", flag ? "-" : "", "" + days, (hours < 10 ? "0" : "") + hours, (minutes < 10 ? "0" : "") + minutes);
    }

    public static String getMinTime(){
        return "2020-10-21T19:50";
    }

    public static String getMaxTime(){
        return "9999-12-31T23:59";
    }

    public static String getDateTimeAttributes(){
        return "min=\"" + getMinTime() + "\" max=\"" + getMaxTime() + "\"";
    }
}
