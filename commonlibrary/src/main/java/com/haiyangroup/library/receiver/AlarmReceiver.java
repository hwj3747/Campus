package com.haiyangroup.library.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;

import com.haiyangroup.library.utils.SystemEvent;
import com.haiyangroup.library.utils.common.ToastManager;

import java.io.IOException;

/**
 * “21”次习题练习强化
 */
public class AlarmReceiver extends BroadcastReceiver {

    public static final String ALARM_START = "com.haiyangroup.library.alarm_start";
    public static final String ALARM_SNOOZE = "com.haiyangroup.library.alarm_snooze";
    public static final String ALARM_DISMISS = "com.haiyangroup.library.alarm_dismiss";
    public static String ALARM_EXERCISE_DAYLY = "com.eln.alarm.daily";
    public static String ALARM_HOT_WEEKLY = "com.eln.alarm.hot";//todolist热门回答
    final public static String GET_LEARN_PLAN_LIST = "com.eln.alarm.get.learnplan";

    protected static AlertDialog.Builder mDialog;
    private Context mContext;
    private MediaPlayer Mp;

    public void onReceive(Context context, Intent intent) {
        mContext = context;
        switch (intent.getAction()) {
            case GET_LEARN_PLAN_LIST:
                Message msg = new Message();
                msg.what = RISC.TEST;
                SystemEvent.fireEvent(msg);
                break;
            case ALARM_START:
                ToastManager.showToast("您预约的车位时间到了!");
                ringtone();
                break;
            case ALARM_SNOOZE:
                break;
            case ALARM_DISMISS:
                break;
        }
    }

    private void ringtone() {
        try {
            //默认铃声
            Mp = new MediaPlayer();
            Mp.setDataSource(mContext, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
            Mp.prepare();
            Mp.start();

            //手机震动
//            Vibrator vib = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
//            long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
//            vib.vibrate(pattern,4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        Log.v("wangxianming", "AlarmKlaxon.stop()");
        Vibrator mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);

        Intent alarmDone = new Intent(ALARM_START);
//        sendBroadcast(alarmDone);

        // Stop audio playing
        if (Mp != null) {
            Mp.stop();
            Mp.release();
            Mp = null;
        }
        // Stop vibrator
        mVibrator.cancel();
//        disableKiller();
    }


}
