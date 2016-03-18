package com.qiktone.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 14:31
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils{
    private static Calendar cal = Calendar.getInstance();

    /**
     * 获取指定日期的年份
     * @param date
     * @return
     */
    public static int getFullYear(Date date) {
        try {
            cal.setTime(date);
            int i = cal.get(1);
            return i;
        } finally {
            cal.clear();
        }
    }

    /**
     * 获取当前的年份
     * @return
     */
    public static int getCurrentYear() {
        return cal.get(1);
    }

    /**
     * 日期按yyy-MM-dd格式转换为字符串
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 日期按给定的格式转换为字符串
     * @param date
     * @param format
     * @return
     */
    public static String getDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     *获取给定日期的下一日期
     * @param date
     * @param n 天
     * @param base 周
     * @return
     */
    public static Date getNextDate(Date date, int n, int base) {
        try {
            cal.setTime(date);
            int week = cal.get(7);
            if (week >= n)
                cal.add(5, -(week - n) + 7 * base);
            else {
                cal.add(5, n - week);
            }
            Date localDate = cal.getTime();
            return localDate;
        } finally {
            cal.clear();
        }
    }


}
