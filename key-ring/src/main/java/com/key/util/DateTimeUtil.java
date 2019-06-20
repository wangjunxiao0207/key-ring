package com.key.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期 与 字符串的转换工具
 */
public class DateTimeUtil {
    // joda-time

    // str->Date
    // Date->str
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date, String formatStr) {
        if(date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date strToDate(String dateTimeStr) {
        return strToDate(dateTimeStr, STANDARD_FORMAT);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, STANDARD_FORMAT);
    }

    /**
     * 获取年
     * @param date
     * @return
     */
    public static int year(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     * @param date
     * @return
     */
    public static int month(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取月份的天数
     * @param date
     * @return
     */
    public static int days(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 转换日期格式， 如 yyyy-MM-dd  -> yyyy-MM
     * @param date
     * @param s
     * @return
     */
    public static Date toFormat(Date date, String s) {
       return strToDate(dateToStr(date, s),s);
    }

    /**
     * 把时间戳转换成Date
     * @param timestamp
     * @return
     */
    public static Date getDate(long timestamp) {
        Date d = new Date();
        d.setTime(timestamp);
        return d;
    }
}
