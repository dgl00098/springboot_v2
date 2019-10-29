package com.fc.test.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类 默认使用 "yyyy-MM-dd HH:mm:ss" 格式化日期
 */
public final class TZDateUtils {
    /**
     * 英文简写（默认）如：23:15:06
     */
    public static String FORMAT_TIME = "HH:mm:ss";
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写 如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 纯数字时间  如:20191008103656
     */
    public static String FORMAT_TIME_TO_NUMBER = "yyyyMMddHHmmss";

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    /**
     * 根据预设格式返回当前日期
     *
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }


    /**
     * 返回纯数字的年月日时分秒时间戳:20191008103656
     *
     * @param date
     * @return
     */
    public static String formatTimeToNumber(Date date) {
        if (date == null) {
            return null;
        }
        return format(date, FORMAT_TIME_TO_NUMBER);
    }

    public static String getInstantNow(Instant instant) {
        if (instant == null) {
            return null;
        }
        Date tmpDate = Date.from(instant);
        return format(tmpDate);
    }

    /**
     * 根据用户格式返回当前日期
     *
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    public static Date getDateTime(Date date) {
        return parse(format(date, FORMAT_TIME), FORMAT_TIME);
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加n分钟
     *
     * @param date
     * @param n    分钟数
     * @return
     */
    public static Date addOneMinute(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, n);
        return cal.getTime();
    }


    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @author dgl
     * @param starttime 时间参数 1 格式：1990-01-01 12:00:00
     * @param endtime 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String starttime, String endtime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(starttime);
            two = df.parse(endtime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //因前端App需要解析该返回值,所以下面的返回格式禁止修改.
        return hour + "-" + min + "-" + sec;
    }


    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }

    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 比较两个时间的大小 HH:mm:ss
     *
     * @param time1 时间字符串
     * @param time2 时间字符串
     * @return boolean
     */
    public static boolean compare(String time1, String time2) {
        try {
            //如果想比较日期则写成"yyyy-MM-dd"就可以了
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_TIME);
            //将字符串形式的时间转化为Date类型的时间
            Date a = sdf.parse(time1);
            Date b = sdf.parse(time2);
            //如果 a的时间比 b的时间晚，返回 true；否则返回 false。
            if (a.before(b))
                return true;
        } catch (Exception e) {
        }
        return false;

    }

    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }


    public static Date parseIncludeEmpty(String strDate) {
        if (StringUtils.isNotEmpty(strDate)) {
            return null;
        } else {
            return parse(strDate);
        }

    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    public static int getYears(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.getYear();
    }

    public static int getMonth(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.getMonthValue();
    }

    public static int getDayOfMonth(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.getDayOfMonth();
    }

    public static int getDayOfYear(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.getDayOfYear();
    }

    public static int getHour(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.getHour();
    }

    /**
     * 获取本月第一天(月初时间)
     */
    public static String getFirstDay0fMonth() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        //1:本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将时分秒清零
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        String time = format(c.getTime(), "yyyy-MM-dd HH:mm:ss");
        return time;
    }

    /**
     * 获取本月最后一天(月底时间)
     */
    public static String getEndDay0fMonth() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        //1:本月第一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将时分秒设置为最大
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        String time = format(c.getTime(), "yyyy-MM-dd HH:mm:ss");
        return time;
    }

    @Scheduled(cron = "00 */1 * * * ?")
    public static void main(String[] args) {
        /*String test1="16:40:06";
        String test2="15:30:07";
        System.out.println(""+compare(test1,test2));*/

        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_TIME);
        //将字符串形式的时间转化为Date类型的时间
        try {
            Date a = sdf.parse("16:45:00");
            Date addDate = TZDateUtils.addOneMinute(a, 30);
            System.out.printf(":" + format(addDate, FORMAT_TIME));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static boolean isSameDay(Date date1, Date date2) {
        return getYears(date1) == getYears(date1) && getMonth(date1) == getMonth(date2) && getDayOfMonth(date1) == getDayOfMonth(date2);
    }

    /**
     * 因为数据库里获取不到用户输入的endtime，所以增加此方法+1天，从而获取用户过滤的日期
     *
     * @param endTime
     * @return
     */
    public static String getEndTime(String endTime) {
        if (StringUtils.isEmpty(endTime)) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date time = calendar.getTime();
        String result = format.format(time);
        return result;
    }

    /**
     * 获取yyyy-MM-dd 格式的当前时间
     *
     * @return
     */
    public static Date getSimpleNow() {
        Date now = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(now);
        // 将时分秒,毫秒域清零
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        return cal1.getTime();
    }

    /**
     * 判断是否为当日
     *
     * @return
     */
    public static boolean isToday(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        return year1 == year2 && month1 == month2 && day1 == day2;
    }

    /**
     * 判断是否为当日
     *
     * @return
     */
    public static boolean isToday(Instant date) {
        if (date == null) {
            return false;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(Date.from(date));
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        return year1 == year2 && month1 == month2 && day1 == day2;
    }

}