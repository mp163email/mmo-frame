package com.mmo.frame.utils;

import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by yuchen on 2016/6/1.
 */
public class TimeUtils {

    /**
     * 时间格式 - -
     */
    public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式 - -
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";


    /**
     * 时间格式 / /
     */
    public static final String FORMAT_TIME_1 = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日期格式 / /
     */
    public static final String FORMAT_DATE_1 = "yyyy/MM/dd";

    /**
     * 一小时毫秒数
     */
    public static final long HOUR_MS = 1000L * 60 * 60;

    /**
     * 一天毫秒数
     */
    public static final long DAY_MS = HOUR_MS * 24;

    /**
     * 一天秒数
     */
    public static final int DAY_SE = 24 * 60 * 60;

    /**
     *
     */
    private static Int2IntArrayMap WEEKDAY_MAPPING = new Int2IntArrayMap();

    static {
        WEEKDAY_MAPPING.put(Calendar.MONDAY, 1);
        WEEKDAY_MAPPING.put(Calendar.TUESDAY, 2);
        WEEKDAY_MAPPING.put(Calendar.WEDNESDAY, 3);
        WEEKDAY_MAPPING.put(Calendar.THURSDAY, 4);
        WEEKDAY_MAPPING.put(Calendar.FRIDAY, 5);
        WEEKDAY_MAPPING.put(Calendar.SATURDAY, 6);
        WEEKDAY_MAPPING.put(Calendar.SUNDAY, 7);
    }

    /**
     * 计算当日0点时间，含时区影响
     *
     * @param timeMS 毫秒时间戳
     * @return 当日0点时间毫秒数
     */
    public static long calcMidnightMS(long timeMS) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(timeMS);

        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTimeInMillis();
    }

    /**
     * 计算两个时间间隔几天
     * @param startSecond
     * @param endSecond
     * @return
     */
    public static int calcTwoDayDiffDays (int startSecond, int endSecond) {
        if (endSecond <= startSecond) {
            return 0;
        }
        return (calcMidnightSeconds(endSecond) - calcMidnightSeconds(startSecond)) / (24 * 60 * 60);
    }

    /**
     * 计算当日0点时间，含时区影响
     *
     * @param timeSeconds 秒时间戳
     * @return 当日0点时间秒数
     */
    public static int calcMidnightSeconds(int timeSeconds) {
        return (int) (calcMidnightMS(timeSeconds * 1000L) / 1000);
    }

    /**
     * 计算当月的1号0点时间
     *
     * @param timeMS
     * @return
     */
    public static long calcMonthBeginningMS(long timeMS) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(timeMS);

        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.AM_PM, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }

    /**
     * 检查指定的两个 毫秒 时间是否处于同一天，以0点为天分界线
     *
     * @param timeMS1
     * @param timeMS2
     * @return
     */
    public static boolean isInSameDay(long timeMS1, long timeMS2) {
        return calcMidnightMS(timeMS1) == calcMidnightMS(timeMS2);
    }

    /**
     * 检查指定的两个 秒 时间是否处于同一天，以0点为天分界线
     *
     * @param timeSeconds1
     * @param timeSeconds2
     * @return
     */
    public static boolean isInSameDay(int timeSeconds1, int timeSeconds2) {
        return isInSameDay(timeSeconds1 * 1000L, timeSeconds2 * 1000L);
    }

    /**
     * 根据当前毫秒数获得 时间-yyyy-MM-dd HH:mm:ss格式
     * @param mill
     * @return
     */
    public static String getTimeByMill(long mill) {
        return new SimpleDateFormat(FORMAT_TIME).format(mill);
    }

    /**
     * 根据当前秒数获得 时间格式：yyyy-MM-dd HH:mm:ss
     * @param sec
     * @return
     */
    public static String getTimeBySec(int sec) {
        return new SimpleDateFormat(FORMAT_TIME).format(sec * 1000L);
    }

    /**
     * 根据时间获取毫秒数 支持yyyy-MM-dd HH:mm:ss 和 yyyy-MM-dd两种格式
     * @param time
     * @return
     */
    public static long getMillByTime (String time) throws ParseException {
        if (time.length() == 10) {
            if (time.contains("/")) {
                return new SimpleDateFormat(FORMAT_DATE_1).parse(time).getTime();
            } else {
                return new SimpleDateFormat(FORMAT_DATE).parse(time).getTime();
            }
        } else {
            if (time.contains("/")) {
                return new SimpleDateFormat(FORMAT_TIME_1).parse(time).getTime();
            } else {
                return new SimpleDateFormat(FORMAT_TIME).parse(time).getTime();
            }
        }
    }

    /**
     * 获取指定每周一的时间（周一到周日）为一周
     * @param time
     * @return
     */
    public static long getMondayMidnightTime(long time) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(time);

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.setTimeInMillis(time - (1000 * 60 * 60 * 24));
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取指定每周一的时间（周一到周日）为一周
     * @param time
     * @return
     */
    public static int getMondayMidnightTime(int time) {
        return (int)(getMondayMidnightTime(time * 1000L) / 1000);
    }

    /**
     * 得到当前毫秒数时间的星期几，星期日返回7
     *
     * @param time 毫秒数
     * @return 星期几
     */
    public static int getWeekDay(long time) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(time);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int mappedDayOfWeek = WEEKDAY_MAPPING.get(dayOfWeek);

        return mappedDayOfWeek;
    }

    /**
     * 从Calendar的DAY_OF_WEEK的值转成普通意义上的1~7
     * @param weekday
     * @return
     */
    public static int convertCalendarWeekday2CommonWeekday(int weekday){
        if (!WEEKDAY_MAPPING.containsKey(weekday))
            return -1;
        return WEEKDAY_MAPPING.get(weekday);
    }

    /**
     * 取得离指定的小时最近的毫秒值
     * @param currentMiddleNightMillis
     * @param hour
     * @param now
     * @return
     */
    public static long getLastestHourMillis(long currentMiddleNightMillis, int hour, Calendar now) {
        long l = currentMiddleNightMillis + hour * 3600000L;
        if (now.get(Calendar.HOUR_OF_DAY) < hour) {
            /**
             * 还没有到刷新小时，按前一天算
             */
            l -= (24L * 3600L * 1000L);
        }

        return l;
    }

    /**
     * 取得离指定周几的指定小时最近的毫秒值
     * @param mondayMidnightTime
     * @param weekdayWanted
     * @param hour
     * @param now
     * @return
     */
    public static long getLastestWeekMillis(long mondayMidnightTime, int weekdayWanted, int hour, Calendar now) {
        long l = mondayMidnightTime;
        if (hour > 0) {
            l += hour * 3600L * 1000L;
        }

        if (weekdayWanted > 1)
            l += 24L * 3600L * 1000L * (weekdayWanted - 1);

        int weekday = TimeUtils.convertCalendarWeekday2CommonWeekday(now.get(Calendar.DAY_OF_WEEK));
        if (weekday < weekdayWanted ||
                (weekday == weekdayWanted && now.get(Calendar.HOUR_OF_DAY) < hour)) {
            /**
             * 还没有到刷新小时，按前一周算
             */
            l -= (7 * 24L * 3600L * 1000L);
        }

        return l;
    }
}
