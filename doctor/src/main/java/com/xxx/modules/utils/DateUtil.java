package com.xxx.modules.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
    
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 获取当前时间字符串
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(new Date());
    }
    
    /**
     * 格式化日期
     */
    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
    
    /**
     * 格式化日期（默认格式）
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_FORMAT);
    }
}

