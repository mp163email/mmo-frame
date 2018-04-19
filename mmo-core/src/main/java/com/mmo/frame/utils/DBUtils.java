package com.mmo.frame.utils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by 李忠波 on 2018/1/6 0006.
 */
public class DBUtils {

    /**
     * 将数据库的指定列读取为int时间戳
     * @param rs
     * @param columnLabel
     * @return
     */
    public static int getDatetime(ResultSet rs, String columnLabel) throws SQLException {
        Calendar calendarNow = Calendar.getInstance();

        Date opentimeDate = rs.getDate(columnLabel, calendarNow);
        Time opentimeTime = rs.getTime(columnLabel, calendarNow);
        int opentime = 0;
        if (opentimeDate != null) {
            opentime = (int) ((opentimeDate.getTime() + opentimeTime.getTime()) / 1000);
            opentime += 8 * 3600;

            /*
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(opentime * 1000L);
            System.out.println("时间 :" +
                    calendar.get(Calendar.YEAR) +
                            (calendar.get(Calendar.MONTH) + 1) +
                    calendar.get(Calendar.DAY_OF_MONTH) +
                    calendar.get(Calendar.HOUR_OF_DAY) +
                    calendar.get(Calendar.MINUTE) +
                    calendar.get(Calendar.SECOND));
                    */
        }

        return opentime;
    }
}
