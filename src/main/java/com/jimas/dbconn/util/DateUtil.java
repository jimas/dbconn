package com.jimas.dbconn.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    
    public static final String TIMESTAMP_ALL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String SHORT_MONTH_FORMAT = "yyyyMM";

    public static final String SHORT_DATE_FORMAT = "yyyyMMdd";
    
    private DateUtil() {
    };
    
    /**
     * 日期转字符串，输出格式 "yyyy-MM-dd"
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            return formatter.format(date);
        } catch (Exception e) {
            return null;
        }
    }
    public static String format(Date date,String format){
        try {
            if (format != null && !"".equals(format) && date != null) {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                return formatter.format(date);
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }
    
    /**
     * 智能匹配日期格式 格式： yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm:ss.S HH:mm:ss yyyy-MM-dd yyyyMM yyyyMMdd
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static Date parseStrAutoToDate(String str) {
        DateFormat df = null;
        if (str == null) {
            return null;
        }
        String s = str.replaceAll("/", "-");
        Date d = null;

        if (s.contains(":")) {
            if (s.length() == DATE_TIME_FORMAT.length())
                df = new SimpleDateFormat(DATE_TIME_FORMAT);
            else if (s.length() == TIME_FORMAT.length())
                df = new SimpleDateFormat(TIME_FORMAT);
            else if (s.length() == TIMESTAMP_FORMAT.length())
                df = new SimpleDateFormat(TIMESTAMP_FORMAT);
            else if (s.length() == TIMESTAMP_ALL_FORMAT.length())
                df = new SimpleDateFormat(TIMESTAMP_ALL_FORMAT);
            else if(s.length()==DATE_MINUTE_FORMAT.length())
                df=new SimpleDateFormat(DATE_MINUTE_FORMAT);
        } else if (s.contains("-")) {
            if (s.length() == DATE_FORMAT.length()) {
                df = new SimpleDateFormat(DATE_FORMAT);
            }
        } else {
            if (s.length() == SHORT_DATE_FORMAT.length())
                df = new SimpleDateFormat(SHORT_DATE_FORMAT);
            else if (s.length() == SHORT_MONTH_FORMAT.length())
                df = new SimpleDateFormat(SHORT_MONTH_FORMAT);
            else
                df = new SimpleDateFormat(DATE_FORMAT); // 默认值
        }
        try {
            d = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
    
    /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date;
        try {
            date = simpleDateFormat.parse(dateStr);
            long ts = date.getTime();
            return   String.valueOf(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
