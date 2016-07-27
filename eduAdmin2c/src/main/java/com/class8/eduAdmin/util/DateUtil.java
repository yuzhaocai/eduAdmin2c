package com.class8.eduAdmin.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final String YYYY_YEAR_MM_MONTH_DD_DATE = "yyyy年MM月dd日";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_BIAS_MM_BIAS_DD = "yyyy/MM/dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";
    public static final String HH = "HH";
    public static final String MI = "mm";
    public static final String SS = "ss";
    public static final String SIMPLE_YYYY_MM_DD = "yyyyMMdd";
    
    /**
     * 当前时间的秒数
     * @return
     */
    public static int currentTimeSeconds(){
    	return (int) (System.currentTimeMillis() / 1000L);
    }
    
    public static Date parseDate(String date, String pattern) throws ParseException {
        if(date == null || date == "") throw new NullPointerException("input date is null or empty!");
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }
    
    public static String formatDate(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Timestamp toTimestamp(Date date){
        return new Timestamp(date.getTime());
    }

    public static Timestamp toTimestamp(String date,String pattern) throws ParseException{
        return toTimestamp(parseDate(date, pattern));
    }
    
    public static String formatDate(String date, String originalPattern,String transformPattern) throws ParseException{
        return formatDate(parseDate(date, originalPattern), transformPattern);
    }
    
    public static String formatTimestamp(Timestamp timestamp,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(timestamp);
    }

    public static Timestamp getBeginTimeOfDay(Date calBeginDate) throws ParseException {
        String date = formatDate(calBeginDate, YYYY_MM_DD);
        date = date + " 00:00:00.000";
        return toTimestamp(date,YYYY_MM_DD_HH_MM_SS_SSS);
    }
    
    public static Timestamp getEndTimeOfDay(Date calBeginDate) throws ParseException {
        String date = formatDate(calBeginDate, YYYY_MM_DD);
        date = date + " 23:59:59.999";
        return toTimestamp(date,YYYY_MM_DD_HH_MM_SS_SSS);
    }

    public static Date getFirstDayOfMonth(String date,String format){  
        Date d = null;
        try {
            d = parseDate(date, format);
            return calFirstDayOfMonth(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    } 
    
    public static Date getFirstDayOfMonth(Date date){
        return calFirstDayOfMonth(date);
    }

    private static Date calFirstDayOfMonth(Date date) {
        Calendar firstDate = null;
        try {
            firstDate = Calendar.getInstance();  
            firstDate.setTime(date);
            firstDate.set(Calendar.DATE,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstDate.getTime();
    }
    
    public static Date getLastDayOfMonth(String date, String format) {
        Date d = null;
        try {
            d = parseDate(date, format);
            return calLastDayOfMonth(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Date getLastDayOfMonth(Date date){
        return calLastDayOfMonth(date);
    }

    private static Date calLastDayOfMonth(Date date) {
        Calendar lastDate = null;
        try {
            lastDate = Calendar.getInstance();
            lastDate.setTime(date);
            lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
            lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
            lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastDate.getTime();
    }

    public static int getDaysOfMonth(Date date){
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.valueOf(formatDate(date,YYYY )));
        c.set(Calendar.MONTH, Integer.valueOf(formatDate(date,MM ))-1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
