package com.haiyangroup.library.utils;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.haiyangroup.library.log.LogUtils;
import com.haiyangroup.library.log.LogUtils.LogCategory;
import com.haiyangroup.library.utils.common.EnvironmentUtils;
import com.haiyangroup.library.utils.common.ToastManager;

import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {
    
    private static CrashHandler INSTANCE;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    
    private CrashHandler() {

    }
    
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public synchronized static void init(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
            INSTANCE.mContext = context;
            INSTANCE.mContext = context;
            INSTANCE.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(INSTANCE);
        }

    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(thread, ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            if (thread.getId() == EnvironmentUtils.getMainThreadId()) {
                //是主线程的Exception，则退出
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e(LogCategory.UNCAUGHT.toString(), "error : ", e);
                }
                //退出程序  
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }  
    }

    private boolean handleException(Thread thread, Throwable ex) {
        if (ex == null) {
            return false;
        }

        if (thread.getId() == com.haiyangroup.library.utils.common.EnvironmentUtils.getMainThreadId()) {
            //是主线程的Exception则弹出退出提示
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    ToastManager.showToast(ToastManager.TOAST_ICON_ERROR,
                            Toast.LENGTH_LONG,
                            "很抱歉，程序出现错误，即将退出...");
                    Looper.loop();
                }
            }.start();
        } else if (!LogUtils.RELEASE) {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    ToastManager.showToast(ToastManager.TOAST_ICON_ERROR,
                            "后台有线程发生错误，已经记录到Log文件中，请查看。");
                    Looper.loop();
                }
            }.start();
        }

        LogUtils.wtf(LogCategory.UNCAUGHT, Log.getStackTraceString(ex));
        return true;
    }
}
