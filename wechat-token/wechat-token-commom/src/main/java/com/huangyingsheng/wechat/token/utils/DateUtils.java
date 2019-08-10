package com.huangyingsheng.wechat.token.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {


    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_SIMPLE_FORMAT = "yyyyMMdd";
    public static String DATE_TIME_FORMAT_TIME = "yyyy-MM-dd HH:mm";
    public static String DATE_FORMAT_CHINESE = "yyyy年M月d日";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_TIMEM_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    public static String DATE_TIME_JAVA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public DateUtils() {
    }

    public static void main(String[] args) {
        String time = "2019-05-07T19:29:29.123Z";
        Instant instant = Instant.parse(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIMEM_FORMAT).withLocale(Locale.SIMPLIFIED_CHINESE).withZone(ZoneId.systemDefault());
        System.out.println(formatter.format(instant));
    }

    public static String getCurrentDate() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    public static String getCurrentSimpleDate() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_SIMPLE_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    public static String getCurrentDateTime() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    public static String getCurrentDateTime(String Dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(Dateformat);
        datestr = df.format(new Date());
        return datestr;
    }

    public static String dateToDateTime(Date date) {
        if (date == null) {
            return null;
        } else {
            String datestr = null;
            SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
            datestr = df.format(date);
            return datestr;
        }
    }

    public static String dateToDate(Date date) {
        if (date == null) {
            return null;
        } else {
            String datestr = null;
            SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT_TIME);
            datestr = df.format(date);
            return datestr;
        }
    }




    public static String dateToString(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        datestr = df.format(date);
        return datestr;
    }

    public static String dateToString(Date date, String dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        datestr = df.format(date);
        return datestr;
    }

    public static int getDayOfDate(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int d = cd.get(5);
        return d;
    }

    public static int getMonthOfDate(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int m = cd.get(2) + 1;
        return m;
    }

    public static int getYearOfDate(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int y = cd.get(1);
        return y;
    }

    public static int getWeekOfDate(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int wd = cd.get(7) - 1;
        return wd;
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(5, 1);
        return cd.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        return addDay(getFirstDayOfMonth(addMonth(date, 1)), -1);
    }

    public static boolean isLeapYEAR(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int year = cd.get(1);
        return year % 4 == 0 && year % 100 != 0 | year % 400 == 0;
    }

    public static Date getDateByYMD(int year, int month, int day) {
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, day);
        return cd.getTime();
    }

    public static Date getYearCycleOfDate(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(1, iyear);
        return cd.getTime();
    }

    public static Date getMonthCycleOfDate(Date date, int i) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(2, i);
        return cd.getTime();
    }

    public static int getYearByMinusDate(Date fromDate, Date toDate) {
        Calendar df = Calendar.getInstance();
        df.setTime(fromDate);
        Calendar dt = Calendar.getInstance();
        dt.setTime(toDate);
        return dt.get(1) - df.get(1);
    }

    public static int getMonthByMinusDate(Date fromDate, Date toDate) {
        Calendar df = Calendar.getInstance();
        df.setTime(fromDate);
        Calendar dt = Calendar.getInstance();
        dt.setTime(toDate);
        return dt.get(1) * 12 + dt.get(2) - (df.get(1) * 12 + df.get(2));
    }

    public static int calcAge(Date birthday, Date calcDate) {
        int cYear = getYearOfDate(calcDate);
        int cMonth = getMonthOfDate(calcDate);
        int cDay = getDayOfDate(calcDate);
        int bYear = getYearOfDate(birthday);
        int bMonth = getMonthOfDate(birthday);
        int bDay = getDayOfDate(birthday);
        return cMonth <= bMonth && (cMonth != bMonth || cDay <= bDay) ? cYear - 1 - bYear : cYear - bYear;
    }

    public static Date addDay(Date date, int iday) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(5, iday);
        return cd.getTime();
    }

    public static Date addMonth(Date date, int imonth) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(2, imonth);
        return cd.getTime();
    }

    public static Date addYear(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(1, iyear);
        return cd.getTime();
    }

    public static String guessFormat(String date) {
        return guessFormat(date, (String)null);
    }

    public static String guessFormat(String date, String defaultFormat) {
        if (date != null && !date.isEmpty()) {
            String format = defaultFormat;
            if (date.contains("年")) {
                format = DATE_FORMAT_CHINESE;
            } else if (date.contains("T")) {
                format = DATE_TIME_JAVA_FORMAT;
            } else if (date.length() == DATE_SIMPLE_FORMAT.length()) {
                format = DATE_SIMPLE_FORMAT;
            } else if (date.length() == DATE_FORMAT.length() && date.contains("-")) {
                format = DATE_FORMAT;
            } else if (date.length() == DATE_TIME_FORMAT.length() && date.contains("-")) {
                format = DATE_TIME_FORMAT;
            } else if (date.length() == DATE_TIME_FORMAT_TIME.length() && date.contains("-")) {
                format = DATE_TIME_FORMAT_TIME;
            } else if (date.length() >= DATE_TIMEM_FORMAT.length() && date.contains("-")) {
                format = DATE_TIMEM_FORMAT;
            }

            return format;
        } else {
            return defaultFormat;
        }
    }



    public static Date getDayStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        return cal.getTime();
    }

    public static Date getDayEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.set(cal.get(1), cal.get(2), cal.get(5), 23, 59, 59);
        return cal.getTime();
    }

    public static String dateStr(Date date) {
        return dateToString(date, DATE_SIMPLE_FORMAT);
    }



    public static int daysBetween(Date startDate, Date endStart) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        Date d1 = sdf.parse(dateStr(startDate));
        Date d2 = sdf.parse(dateStr(endStart));
        cal.setTime(d1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long time2 = cal.getTimeInMillis();
        return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
    }

    public static Date getBeforeDateByHour(Date date, int hour) {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        dateCalendar.add(11, hour);
        return dateCalendar.getTime();
    }

    public static Date getBeforeIntegral(Date date) {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        dateCalendar.set(12, 0);
        dateCalendar.set(13, 0);
        dateCalendar.set(14, 0);
        return dateCalendar.getTime();
    }

    public static Date getLaterIntegral(Date date) {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        dateCalendar.add(11, 1);
        dateCalendar.set(12, 0);
        dateCalendar.set(13, 0);
        dateCalendar.set(14, 0);
        return dateCalendar.getTime();
    }

    public static Calendar getLocalCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
    }


}
