package com.cx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version V1.0
 * @Title: DateUtil.java <br>
 * @Package com.xm.utils <br>
 * @Description: (处理日期相关的工具类) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/3/13 09:53 <br>
 */
public class DateUtil {

    /**
     * @throws
     * @author: 路逸冰(Allen) <br>
     * @date: 2018/3/13 09:54<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: getDays <br>
     * @Description: (获取两个日期相隔的天数) <br>
     * parama: [oldDate] <br>
     * @return: java.util.long <br>
     */
    public static long getDays(String oldDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long beginDate = simpleDateFormat.parse(oldDate).getTime();
            long endDate = new Date().getTime();
            return (endDate - beginDate) / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * @throws
     * @author: 路逸冰(Allen) <br>
     * @date: 2018/3/13 11:25<br>
     * @modify 修改人 <br>
     * @modifyDate: 修改时间 <br>
     * @Title: getCurrentDate <br>
     * @Description: (获取当前时间) <br>
     * @parma: [] <br>
     * @return: java.lang.String<br>
     */
    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    /*
  * 将时间转换为时间戳
  */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
 * 将时间戳转换为时间
 */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
