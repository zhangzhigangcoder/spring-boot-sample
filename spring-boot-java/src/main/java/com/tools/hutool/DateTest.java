package com.tools.hutool;

import cn.hutool.core.date.*;
import com.core.copy.Person;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * @Desc
 * @Author zhangzhigang
 * @Data 2020-09-24 16:51
 */
public class DateTest {
    public static void main(String[] args) {
//        String dateStr1 = "2020-02-02 22:33:23";
//        Date date1 = DateUtil.parse(dateStr1);
//
//        String dateStr2 = "2020-03-01 23:33:23";
//        Date date2 = DateUtil.parse(dateStr2);
//
//        long betweenMonth = DateUtil.betweenMonth(date1, date2, false);
//        System.out.println(betweenMonth);
        DateTime date = DateUtil.date(1609207802000L);
        System.out.println(DateUtil.formatDateTime(date));
    }


    /**
     * 创建
     */
    public static void create() {
        // 当前时间
        DateTime date = DateUtil.date();
        System.out.println(date);

        DateTime date1 = DateUtil.date(Calendar.getInstance());
        System.out.println(date1);

        DateTime date2 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date2);

        // 当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        System.out.println(now);

        // 当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();
        System.out.println(today);
    }

    /**
     * 字符串 -> 日期
     */
    public static void parse() {
        /**
         * yyyy-MM-dd HH:mm:ss
         * yyyy-MM-dd
         * HH:mm:ss
         * yyyy-MM-dd HH:mm
         * yyyy-MM-dd HH:mm:ss.SSS
         */
        String dateStr = "2017-03-01 12:11:30";
        // 默认正则表达式自匹配(建议指定格式)
        DateTime parse = DateUtil.parse(dateStr);
        System.out.println(parse);

        DateTime parse2 = DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
        System.out.println(parse2);
    }

    /**
     * 格式化
     */
    public static void format() {
        String dateStr = "2017-03-01 11:12:20";
        Date date = DateUtil.parse(dateStr);

        // 自定义格式： yyyy/MM/dd
        String format = DateUtil.format(date, "yyyy/MM/dd");
        System.out.println(format);

        // yyyy-MM-dd
        String formatDate = DateUtil.formatDate(date);
        System.out.println(formatDate);

        // yyyy-MM-dd hh:mm:ss
        String formatDateTime = DateUtil.formatDateTime(date);
        System.out.println(formatDateTime);

        // hh:mm:ss
        String formatTime = DateUtil.formatTime(date);
        System.out.println(formatTime);
    }

    /**
     * 获取日期某个部分
     */
    public static void queryField() {
        Date date = DateUtil.date();

        //获得年的部分 yyyy
        int year = DateUtil.year(date);
        System.out.println(year);

        //获得月份，从0开始计数
        int month = DateUtil.month(date);
        System.out.println(month);

        //获得月份枚举
        Month monthEnum = DateUtil.monthEnum(date);
        System.out.println(monthEnum);

        int weekOfYear = DateUtil.weekOfYear(date);
        System.out.println(weekOfYear);

        int weekOfMonth = DateUtil.weekOfMonth(date);
        System.out.println(weekOfMonth);

        int dayOfMonth = DateUtil.dayOfMonth(date);
        System.out.println(dayOfMonth);

        int dayOfYear = DateUtil.dayOfYear(date);
        System.out.println(dayOfYear);

        int dayOfWeek = DateUtil.dayOfWeek(date);
        System.out.println(dayOfWeek);

        int hour = DateUtil.hour(date, true);
        System.out.println(hour);

        int minute = DateUtil.minute(date);
        System.out.println(minute);

        int second = DateUtil.second(date);
        System.out.println(second);

        int millisecond = DateUtil.millisecond(date);
        System.out.println(millisecond);

        boolean isAM = DateUtil.isAM(date);
        System.out.println(isAM);

        boolean isPM = DateUtil.isPM(date);
        System.out.println(isPM);

        /****************根据当前时间获取*****************/

        int thisYear = DateUtil.thisYear();
        System.out.println(thisYear);

        int thisMonth = DateUtil.thisMonth();
        System.out.println(thisMonth);

        Month thisMonthEnum = DateUtil.thisMonthEnum();
        System.out.println(thisMonthEnum);

        int thisWeekOfYear = DateUtil.thisWeekOfYear();
        System.out.println(thisWeekOfYear);

        // ....
    }

    /**
     * 开结束时间
     */
    public static void startAndEnd() {
        String dateStr = "2020-11-05 22:33:23";
        Date date = DateUtil.parse(dateStr);

        // 2020-01-01 00:00:00
        Date beginOfYear = DateUtil.beginOfYear(date);
        System.out.println(beginOfYear);

        // 2020-12-31 23:59:59
        Date endOfYear = DateUtil.endOfYear(date);
        System.out.println(endOfYear);

        // 2020-10-01 00:00:00
        Date beginOfQuarter = DateUtil.beginOfQuarter(date);
        System.out.println(beginOfQuarter);

        // 2020-12-31 23:59:59
        Date endOfQuarter = DateUtil.endOfQuarter(date);
        System.out.println(endOfQuarter);

        // 2020-11-01 00:00:00
        Date beginOfMonth = DateUtil.beginOfMonth(date);
        System.out.println(beginOfMonth);

        // 2020-11-30 23:59:59
        Date endOfMonth = DateUtil.endOfMonth(date);
        System.out.println(endOfMonth);

        // 2020-11-02 00:00:00
        Date beginOfWeek = DateUtil.beginOfWeek(date);
        System.out.println(beginOfWeek);

        // 2020-11-08 23:59:59
        Date endOfWeek = DateUtil.endOfWeek(date);
        System.out.println(endOfWeek);

        // 一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);
        System.out.println(beginOfDay);

        // 一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
        System.out.println(endOfDay);

        // 2020-11-05 22:33:23
        Date beginOfSecond = DateUtil.beginOfSecond(date);
        System.out.println(beginOfSecond);

        // 2020-11-05 22:33:23
        Date endOfSecond = DateUtil.endOfSecond(date);
        System.out.println(endOfSecond);
    }

    /**
     * 设置偏移量
     */
    public static void offset() {
        String dateStr = "2020-11-05 22:33:23";
        Date date = DateUtil.parse(dateStr);

        // 自定义偏移单位、偏移量
        // 2020-11-07 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
        System.out.println(newDate);

        // 2021-02-05 22:33:23
        DateTime offsetMonth = DateUtil.offsetMonth(date, 3);
        System.out.println(offsetMonth);

        // 2020-11-26 22:33:23
        DateTime offsetWeek = DateUtil.offsetWeek(date, 3);
        System.out.println(offsetWeek);

        // 2020-11-08 22:33:23
        DateTime offsetDay = DateUtil.offsetDay(date, 3);
        System.out.println(offsetDay);

        // 2020-11-05 19:33:23
        DateTime offsetHour = DateUtil.offsetHour(date, -3);
        System.out.println(offsetHour);

        // 2020-11-05 22:30:23
        DateTime offsetMinute = DateUtil.offsetMinute(date, -3);
        System.out.println(offsetMinute);

        // 2020-11-05 22:33:20
        DateTime offsetSecond = DateUtil.offsetSecond(date, -3);
        System.out.println(offsetSecond);

        /*************** 基于当前时间计算 ******************/

        Date lastMonth = DateUtil.lastMonth();
        System.out.println(lastMonth);

        Date nextMonth = DateUtil.nextMonth();
        System.out.println(nextMonth);

        Date lastWeek = DateUtil.lastWeek();
        System.out.println(lastWeek);

        Date nextWeek = DateUtil.nextWeek();
        System.out.println(nextWeek);

        Date yesterday = DateUtil.yesterday();
        System.out.println(yesterday);

        Date tomorrow = DateUtil.tomorrow();
        System.out.println(tomorrow);
    }

    /**
     * 计算相差时间
     */
    public static void between() {
        String dateStr1 = "2020-02-02 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2020-03-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        // 自定义比较单位
        long between = DateUtil.between(date1, date2, DateUnit.DAY);
        System.out.println(between);

        // isReset 是否重置到第一天进行计算
        long betweenYear = DateUtil.betweenYear(date1, date2, true);
        System.out.println(betweenYear);

        long betweenMonth = DateUtil.betweenMonth(date1, date2, true);
        System.out.println(betweenMonth);

        long betweenWeek = DateUtil.betweenWeek(date1, date2, true);
        System.out.println(betweenWeek);

        long betweenDay = DateUtil.betweenDay(date1, date2, true);
        System.out.println(betweenDay);

        long betweenMs = DateUtil.betweenMs(date1, date2);
        System.out.println(betweenMs);
    }

    /**
     * 计算相差时间，并格式化
     */
    public static void formatBetween() {
        String dateStr1 = "2020-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2021-04-02 23:34:24";
        Date date2 = DateUtil.parse(dateStr2);

        long betweenMs = DateUtil.betweenMs(date1, date2);

        // 自定义精度
        // xxx天xx小时xx分xx秒
        String formatBetween = DateUtil.formatBetween(date1, date2, BetweenFormater.Level.SECOND);
        System.out.println(formatBetween);

        // 默认精度为毫秒
        // xxx天xx小时xx分xx秒xx毫秒
        String formatBetween2 = DateUtil.formatBetween(date1, date2);
        System.out.println(formatBetween2);

        // 自定义精度
        // xxx天xx小时xx分
        String formatBetween3 = DateUtil.formatBetween(betweenMs, BetweenFormater.Level.MINUTE);
        System.out.println(formatBetween3);

        // 默认精度毫秒
        // xxx天xx小时xx分xx秒xx毫秒
        String formatBetween4 = DateUtil.formatBetween(betweenMs);
        System.out.println(formatBetween4);
    }

}
