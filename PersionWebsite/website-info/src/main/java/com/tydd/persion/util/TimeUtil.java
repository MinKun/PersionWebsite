package com.tydd.persion.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TimeUtil
 * @Description 时间工具类
 * @Author kun
 * @Date 2019/3/3
 * @Version 1.0
 **/
public class TimeUtil {

    /**
     * 获取时间戳（入参时间）
     * @param date
     * @return
     */
    public static String getTimestamp (Date date) {
        return date.toString();
    }

    /**
     * 获取当前日期的时间戳
     * @return
     */
    public static Long getTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取时间戳（系统当前时间）
     * @return
     */
    public static String getTimestamp () {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取当前日期
     * @return
     */
    public static Date getTime() {
        return new Date();
    }

    /**
     * 获取时间的格式
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        String dataString = null;
        if (!StringUtil.isEmpty(format) && date != null) {
            dataString = new SimpleDateFormat(format).format(date);
        }
        return dataString;
    }

    /**
     * 时间戳转时间格式
     * @param lt
     * @return
     */
    public static String stampToDate(Long lt){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}