package com.haiyangroup.library.utils.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.haiyangroup.library.AbsConstant;
import com.haiyangroup.library.receiver.AlarmReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InitManager {

    private static final int ALARM_ID_TEST = 0;//相同的id不应该存在
    private static String ALARM_AM = "12:00";
    private static String ALARM_PM = "18:00";
    private static String ALARM_AM_8 = "8:00";
    private static String ALARM_SAT = "19:00";
    private static String ALARM_SUN = "20:00";
    private static InitManager mInstance;
    private PendingIntent piAM = null;

    public static InitManager getInstance() {
        if (mInstance == null) {
            mInstance = new InitManager();
        }
        return mInstance;
    }

    public void init(Context context) {
    }

    public void initTestOneMin(Context context) {
        Intent intent = new Intent(AlarmReceiver.ALARM_START);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), pi);
    }

    /**
     * 设置闹钟周期
     *
     * @param context   上下文
     * @param startDate 开始时间
     * @param week      重复周期
     */
    public void initTest(Context context, String startDate, String week, int requestCode) {
        //根据请求码分别设置计时器
        Intent startIntent = new Intent(AlarmReceiver.ALARM_START);
        piAM = PendingIntent.getBroadcast(context, requestCode, startIntent, 0);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //根据周期判断是否重复
        if (week == null || week.equals("")) {
            am.set(AlarmManager.RTC_WAKEUP, getNextAlarmTime(startDate), piAM);
        } else {
            am.setRepeating(AlarmManager.RTC_WAKEUP, getNextAlarmTime(startDate), getNextAlarmByWeeks(week, startDate), piAM);
        }
    }

    /**
     * 关闭闹钟
     *
     * @param context 上下文
     */
    public void closeAlarm(Context context, int requestCode) {
        //根据请求码分别设置计时器
        Intent startIntent = new Intent(AlarmReceiver.ALARM_START);
        piAM = PendingIntent.getBroadcast(context, requestCode, startIntent, 0);

        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        am.cancel(piAM);
    }

    private long getNextOneMin() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + 5);
        return c.getTimeInMillis();
    }

    /**
     * 初始化21次法则的强化练习
     */
    public void initExercise(Context context) {
        //定义一个接收器的KEY
        Intent intent = new Intent(AlarmReceiver.ALARM_EXERCISE_DAYLY);
        //通过不同requestCode  区分不同的定时器
        PendingIntent piAm = PendingIntent.getBroadcast(context, 0, intent, 0);
        PendingIntent piPm = PendingIntent.getBroadcast(context, 1, intent, 0);
        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP, getNextAlarmTime(ALARM_AM), AbsConstant.DAY, piAm);
        am.setRepeating(AlarmManager.RTC_WAKEUP, getNextAlarmTime(ALARM_PM), AbsConstant.DAY, piPm);
    }

    /**
     * 初始化热门回答查看
     */
    private void initHot(Context context) {
        Intent intent = new Intent(AlarmReceiver.ALARM_HOT_WEEKLY);
        //通过不同requestCode  区分不同的定时器
        PendingIntent piSat = PendingIntent.getBroadcast(context, 3, intent, 0);
        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP, getNextAlarmTimeByWeek(ALARM_SAT, Calendar.SATURDAY), AbsConstant.WEEK, piSat);
    }

    /**
     * 重新初始化闹钟
     **/
    public void resetPlanAlarm(Context context) {
        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AlarmReceiver.ALARM_EXERCISE_DAYLY);

        PendingIntent pi = PendingIntent.getBroadcast(context, 5, intent, 0);
        am.cancel(pi);
    }

    //获取N小时后的时间
    private Long getNextNHour(String time, int n) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        Calendar c = Calendar.getInstance();
        try {
            fmt.applyPattern("yyyy-MM-dd HH:mm:ss");
            Date d = fmt.parse(time);
            c.setTime(d);
            long now = System.currentTimeMillis();
            long nextTime = c.getTimeInMillis();
            if (now >= nextTime) {
                c.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + n);
                return c.getTimeInMillis();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c.getTimeInMillis();
    }

    public static void cancelAlarm(Context context) {
        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AlarmReceiver.ALARM_EXERCISE_DAYLY);

		/*PendingIntent piAm = PendingIntent.getBroadcast(context, 0, intent, 0);
        PendingIntent piPm = PendingIntent.getBroadcast(context, 1, intent, 0);
        PendingIntent piAm8 = PendingIntent.getBroadcast(context, 2, intent, 0);
        PendingIntent piSat = PendingIntent.getBroadcast(context, 3, intent, 0);
        PendingIntent piSun = PendingIntent.getBroadcast(context, 4, intent, 0);
		am.cancel(piAm);
		am.cancel(piPm);
        am.cancel(piAm8);
        am.cancel(piSat);
        am.cancel(piSun);*/
    }

    /**
     * 获取第一次闹钟时间
     *
     * @param startTime 参数格式为09:00
     * @return  第一次闹钟时间
     */
    public static long getNextAlarmTime(String startTime) {
        final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        final Calendar c = Calendar.getInstance();
        final long now = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date d = df.parse(startTime);
            c.setTimeInMillis(d.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }

        long nextTime;
        nextTime = c.getTimeInMillis();
        // 指定日期已过
        if (now >= nextTime) {
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
            nextTime = c.getTimeInMillis();
        }
//		MLog.e(df.format(c.getTime()));
        return nextTime;
    }

    /**
     * 获取第一次闹钟时间根据星期几
     *
     * @param startTime
     * @param week
     * @return
     */
    public static long getNextAlarmTimeByWeek(String startTime, int week) {
        final SimpleDateFormat fmt = new SimpleDateFormat();
        final Calendar c = Calendar.getInstance();
        final long now = System.currentTimeMillis();
        try {
            fmt.applyPattern("HH:mm");
            Date d = fmt.parse(startTime);
            c.set(Calendar.HOUR_OF_DAY, d.getHours());
            c.set(Calendar.MINUTE, d.getMinutes());
            c.set(Calendar.DAY_OF_WEEK, week);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long nextTime = 0;
        nextTime = c.getTimeInMillis();
        // 指定日期已过
        if (now >= nextTime) {
            c.set(Calendar.DAY_OF_WEEK_IN_MONTH, c.get(Calendar.DAY_OF_WEEK_IN_MONTH) + 1);
            nextTime = c.getTimeInMillis();
        }
        return nextTime;
    }

    /**
     * 按星期提醒，如星期一、星期三 ,  参数dateValue格式：1,3
     * <p/>
     * startTime:为当天开始时间，如上午9点, 参数格式为09:00
     */
    public static long getNextAlarmByWeeks(String dateValue,
                                           String startTime) {
        final Calendar c = Calendar.getInstance();
        final long now = System.currentTimeMillis();

        // 设置开始时间
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date date = fmt.parse(startTime);
            c.setTimeInMillis(date.getTime());
//            c.set(Calendar.HOUR_OF_DAY, date.getHours());
//            c.set(Calendar.MINUTE, date.getMinutes());
//            c.set(Calendar.SECOND, 0);
//            c.set(Calendar.MILLISECOND, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long nextTime = 0;
        final long[] checkedWeeks = parseDateWeeks(dateValue);
        if (null != checkedWeeks) {
            for (long week : checkedWeeks) {
                c.set(Calendar.DAY_OF_WEEK, (int) (week + 1));

                long triggerAtTime = c.getTimeInMillis();
                if (triggerAtTime <= now) { // 下周
                    triggerAtTime += AlarmManager.INTERVAL_DAY * 7;
                }
                // 保存最近闹钟时间
                if (0 == nextTime) {
                    nextTime = triggerAtTime;
                } else {
                    nextTime = Math.min(triggerAtTime, nextTime);
                }
            }
        }
        return nextTime;
    }

    public static long[] parseDateWeeks(String value) {
        long[] weeks = null;
        try {
            final String[] items = value.split(",");
            weeks = new long[items.length];
            int i = 0;
            for (String s : items) {
                weeks[i++] = Long.valueOf(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weeks;
    }

}

