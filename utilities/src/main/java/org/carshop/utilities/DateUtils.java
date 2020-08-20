package org.carshop.utilities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author ly-zhangjiaj
 * @date 2017年11月24日
 */
public class DateUtils {

    public static Timestamp formatTimestamp(String strValue) throws ParseException {
        return new Timestamp(formatDate(strValue).getTime());
    }

    /**
     * 字符串转成util.Date
     * 支持字符串格式:yyy-MM-dd HH:mm:ss,yyyyMMdd HHmmss,yyyy-MM-dd,yyyyMMdd,yyyy,yyyy/MM/dd,yyyy年MM月dd日,yyyy年M月d日,yyyy年M月dd日,yyyy年MM月d日
     *
     * @param strValue
     * @return
     * @throws ParseException
     */
    public static Date formatDate(String strValue) throws ParseException {

        if (strValue == null) return null;

        Map<String, String> mRgx = getFixMap();
        for (Map.Entry<String, String> entry : mRgx.entrySet()) {
            if (Pattern.compile(entry.getValue()).matcher(strValue).matches()) {
                return new SimpleDateFormat(entry.getKey()).parse(strValue);
            }
        }
        throw new ParseException(strValue + "不符合date转换格式", 0);
    }

    private static Map<String, String> fixMap;

    private static Map getFixMap() {
        if (fixMap == null) {
            fixMap = new HashMap();

            //观致对接接口使用
            fixMap.put("yyyy-MM-dd'T'HH:mm:ss.SSS+0000", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}\\+\\d{4}$");
            fixMap.put("yyyy-MM-dd HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}$");
            fixMap.put("yyyy-MM-dd", "^\\d{4}-\\d{1,2}-\\d{1,2}$");
            fixMap.put("yyyyMMdd", "^\\d{4}\\d{2}\\d{2}$");
            fixMap.put("yyyy/MM/dd", "^\\d{4}/\\d{1,2}/\\d{1,2}$");
            fixMap.put("yyyy年MM月dd日", "^\\d{4}年\\d{1,2}月\\d{1,2}日$");

        }
        return fixMap;
    }

    /**
     * util.Date转换成指定格式字符串
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static String formatString(Date date, String format) throws ParseException {

        if (date == null) return "";
        SimpleDateFormat datePaser = new SimpleDateFormat(format);
        return datePaser.format(date);
    }

    /**
     * 字符串转换成指定格式字符串日期
     * 支持字符串格式:yyy-MM-dd HH:mm:ss,yyyyMMdd HHmmss,yyyy-MM-dd,yyyyMMdd,yyyy
     *
     * @param string
     * @param format
     * @return
     * @throws ParseException
     */
    public static String formatString(String string, String format) throws ParseException {
        return formatString(formatDate(string), format);
    }

    /**
     * 获取特定时间特定月份后的最后那个月的最后一天的字符串格式
     *
     * @param dateStr 一个日期格式的字符串
     * @param month
     * @return
     */
    public static String getMonthLastDay(String dateStr, int month) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            if (dateStr != null && !"".equals(dateStr)) {
                calendar.setTime(dft.parse(dateStr));
            }
        } catch (ParseException e) {
        }
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }


    /**
     * 功能简述:<br> 返回当前日期
     * 详细描述:<br>
     *
     * @return
     * @throws ParseException
     * @author 刘伟锐
     * @CreateTime 2019年10月18日下午6:16:50
     */
    public static Date getShorDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getShorDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int num) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, num);
        return ca.getTime();
    }


    public static long getDayDiff(Date date1, Date date2) {
        long diffSeconds = date2.getTime() - date1.getTime();
        long diffHour = diffSeconds / (1000 * 3600);
        System.out.println("获得小时:" + diffHour);
        long diffDay = (long) diffHour / 24;
        return diffDay;
    }

    public static Date addMonth(Date date, int num) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, num);
        return ca.getTime();
    }

    /**
     * 比较日期（年月日）
     */

    public static int dateCompare(Date date1, Date date2) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFirst = null;
        try {
            dateFirst = dateFormat.parse(dateFormat.format(date1));
            Date dateLast = dateFormat.parse(dateFormat.format(date2));

            if (dateFirst.after(dateLast)) {
                return 1;
            } else if (dateFirst.before(dateLast)) {
                return -1;
            }
            return 0;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 生成时间格式的文件名
     */
    public static String buildFileName() {
        //new一个时间对象date
        Date date = new Date();

        //格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //格式化时间，并且作为文件名
        return sdf.format(date);
    }

}
