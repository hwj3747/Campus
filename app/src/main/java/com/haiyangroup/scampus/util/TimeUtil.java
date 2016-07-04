package com.haiyangroup.scampus.util;

import com.haiyangroup.library.utils.SystemEvent;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间处理的工具类
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class TimeUtil {

    static String DEFAULT_PATTERN="yyyy-MM-dd HH:mm:ss";


    /**
     * 把timestamp格式的时间转化为相应格式的时间 <br>
     * 1、通过pattern将timestamp转化为对应格式<br>
     * 2、如果pattern为空，使用默认的格式<br>
     * @param time，时间戳<br>
     * @param pattern，匹配格式<br>
     * @return string
     * @throws IllegalArgumentException 如果pattern不合法
     */
    public static String timestamp2str(Timestamp time, String pattern) {
        if (pattern != null && !"".equals(pattern)) {
            if (!"yyyyMMddHHmmss".equals(pattern)
                    && !"yyyy-MM-dd HH:mm:ss".equals(pattern)
                    && !"yyyy-MM-dd".equals(pattern)
                    && !"MM/dd".equals(pattern)){
                throw new IllegalArgumentException("Date format ["+pattern+"] is invalid");
            }
        }else{
            pattern = DEFAULT_PATTERN;
        }

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTime(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(cal.getTime());
    }


    public static String translateTime(String time) {
        String pattern ="yyyy-MM-dd";

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前日期所对应的星期 <br>
     * 1、获取当前时间<br>
     * 2、通过Calendar.DAY_OF_WEEK得到当前星期<br>

     * @return mWeek
     */
    public static int currentWeekOfDay(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int mWeek = c.get(Calendar.DAY_OF_WEEK);
        return mWeek;
    }


    /**
     * 获取周一到周日的日期列表<br>
     * 1、获取当前日期<br>
     * 2、加上week*7得到第week周的日期<br>
     * 3、计算周一的日期，依次加一得到一整周的日期
     * @param week，要计算的是第几周<br>
     * @return returnList
     */
    public static ArrayList<String> currentWeek(int week){//返回本周一到周日的日期
        ArrayList<String> returnList=new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, week*7);
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        returnList.add(sdf.format(cal.getTime()));
        System.out.println("所在周星期一的日期：" + sdf.format(cal.getTime()));
        System.out.println(cal.getFirstDayOfWeek() + "-" + day + "+6=" + (cal.getFirstDayOfWeek() - day + 6));

        cal.add(Calendar.DATE, 1);
        System.out.println("所在周星期二的日期：" + sdf.format(cal.getTime()));
        returnList.add(sdf.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        System.out.println("所在周星期三的日期：" + sdf.format(cal.getTime()));
        returnList.add(sdf.format(cal.getTime()));

        cal.add(Calendar.DATE,1);
        System.out.println("所在周星期四的日期：" + sdf.format(cal.getTime()));
        returnList.add(sdf.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        System.out.println("所在周星期五的日期：" + sdf.format(cal.getTime()));
        returnList.add(sdf.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        System.out.println("所在周星期六的日期：" + sdf.format(cal.getTime()));
        returnList.add(sdf.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        System.out.println("所在周星期日的日期：" + sdf.format(cal.getTime()));
        returnList.add(sdf.format(cal.getTime()));
        return returnList;
    }

    public static String[] allDay(int number){//返回本周一到周日的日期
        ArrayList<String> returnList=new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        for(int i=0;i<number;i++) {
            cal.add(Calendar.DATE, 1);
            returnList.add( sdf.format(cal.getTime()));
        }
        return returnList.toArray(new String[returnList.size()]);

    }

    public static Boolean compareTime(String s1,String s2) {

        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c2 = java.util.Calendar.getInstance();
        try {
            c1.setTime(df.parse(s1));
            c2.setTime(df.parse(s2));
        } catch (java.text.ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);
        if (result == 0)
            return false;
        else if (result < 0)
            return true;
        else
            return false;
    }
    public static void main(String[] args){
        String s1 = "2008-01-25 09:12";
        String s2 = "2008-01-29 09:17";
        System.out.print(compareTime(s1,s2));
    }
}
