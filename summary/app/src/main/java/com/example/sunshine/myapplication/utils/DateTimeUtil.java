package com.example.sunshine.myapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liyu on 2016/7/7.
 */
public class DateTimeUtil {

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        return sdf.format(now);
    }

    /**
     * 将时间格式为yyyy-MM-dd 转换为时间格式为MM-dd
     *
     * @param dateString
     * @return
     */
    public static String convertDateFormat(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat changedFormat = new SimpleDateFormat("MM-dd");
        Date date = null;
        String changedstring;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            changedstring = dateString;
            e.printStackTrace();
        }
        changedstring = changedFormat.format(date);
        return changedstring;

    }


    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return sdf.format(now);
    }

    public static String getCurrentMonthOnly() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date now = new Date();
        return sdf.format(now);
    }

    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date now = new Date();
        return sdf.format(now);
    }

    public static String getCurrentYearOnly() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date now = new Date();
        return sdf.format(now);
    }

    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 获取某个日期的后几天的日期
     *
     * @param dateString 某日期
     * @param dayNumber  后面第几天
     * @return
     */
    public static String getNextDay(String dateString, int dayNumber) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + dayNumber);
        String nextDay = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return nextDay;
    }

    /**
     * 获取某个日期的前几天的日期
     *
     * @param dateString 某日期
     * @param dayNumber  前面第几天
     * @return
     */
    public static String getPreviousDay(String dateString, int dayNumber) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - dayNumber);

        String previousDay = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return previousDay;
    }

}
