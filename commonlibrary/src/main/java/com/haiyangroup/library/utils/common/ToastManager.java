package com.haiyangroup.library.utils.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haiyangroup.library.R;
import com.haiyangroup.library.log.LogUtils;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ToastManager {
    private static ToastManager sToastManager;
    private Context mContext;
    private boolean mWillCheckNextToast = false;
    private ConcurrentLinkedQueue<ToastInfo> mToastQueue = new ConcurrentLinkedQueue<ToastInfo>();

    private class ToastInfo {
        int toastIcon;
        int duration;
        String message;

        ToastInfo(int toastIcon, int duration, String message) {
            this.toastIcon = toastIcon;
            this.duration = duration;
            this.message = message;
        }

        Toast makeToast() {
            Toast toast = new Toast(mContext);
            toast.setView(getToastView(toastIcon, message));
            toast.setDuration(duration);
            return toast;
        }
    }
    
    public final static long TOAST_TIME_INTERVAL = 1000;
    private static Handler sMainThreadHandler;
    
    public static final int TOAST_ICON_ERROR = R.drawable.ic_toast_error;
//    public static final int TOAST_ICON_MOVE = R.drawable.message_list_item_move;
//    public static final int TOAST_ICON_STAR = R.drawable.message_list_item_staroff;
//    public static final int TOAST_ICON_UNREAD = R.drawable.message_list_item_unread_button;
//    public static final int TOAST_ICON_SEND = R.drawable.message_list_item_send;
//    public static final int TOAST_ICON_SUCCEED = R.drawable.ic_toast_succeed;
    
    private ToastManager() {
        mContext = EnvironmentUtils.getAppContext();
    }
    
    public static ToastManager getToastManagerInstance(){
        if(sToastManager == null)
            sToastManager = new ToastManager();
        
        return sToastManager;
    }
    
    private static Handler getMainThreadHandler() {
        if (sMainThreadHandler == null) {
            // No need to synchronize -- it's okay to create an extra Handler, which will be used
            // only once and then thrown away.
            sMainThreadHandler = new Handler(Looper.getMainLooper());
        }
        return sMainThreadHandler;
    }

    private View getToastView(int toastIcon, String message) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.toast_with_icon, null);
        ViewGroup iconContainer = (ViewGroup)v.findViewById(R.id.toast_icon_container);
        ImageView iconView = (ImageView)v.findViewById(R.id.toast_icon);
        TextView msgView = (TextView)v.findViewById(R.id.toast_message);
        ViewGroup msgLeftMargin = (ViewGroup)v.findViewById(R.id.toast_message_leftmargin);
        
        if (toastIcon > 0) {
            iconView.setImageResource(toastIcon);
            msgLeftMargin.setVisibility(View.GONE);
        } else {
            iconContainer.setVisibility(View.GONE);
        }
        
        msgView.setText(message);
        return v;
    }
    
    private void showToastInternal(int toastIcon, int duration, String message){
        final ToastInfo toastInfo = new ToastInfo(toastIcon, duration, message);
        if (!LogUtils.RELEASE) {
            mToastQueue.offer(toastInfo);
            if (!mWillCheckNextToast) {
                getMainThreadHandler().post(showToastRunnable);
            }
        } else {
            getMainThreadHandler().post(new Runnable() {
                @Override
                public void run() {
                    toastInfo.makeToast().show();
                }
            });
        }
    }

    Runnable showToastRunnable = new Runnable() {
        @Override
        public void run() {
            showToastFromQueue();
        }
    };

    private void showToastFromQueue() {
        ToastInfo toastInfo = mToastQueue.poll();
        if (toastInfo != null) {
            toastInfo.makeToast().show();
            mWillCheckNextToast = true;
            getMainThreadHandler().postDelayed(showToastRunnable, TOAST_TIME_INTERVAL);
        } else {
            mWillCheckNextToast = false;
        }
    }
    
    public static void showToast(int msgResId) {
        showToast(-1, msgResId);
    }
    
    public static void showToast(String message) {
        showToast(-1, message);
    }
    
    public static void showToast(int toastIcon, int msgResId) {
        showToast(toastIcon, Toast.LENGTH_SHORT, msgResId);
    }
    
    public static void showToast(int toastIcon, String message) {
        showToast(toastIcon, Toast.LENGTH_SHORT, message);
    }
    
    public static void showToast(int toastIcon, int duration, int msgResId, Object... args) {
        showToast(toastIcon, duration, getToastManagerInstance().mContext.getResources().getString(msgResId), args);
    }
    
    public static void showToast(int toastIcon, int duration, String message, Object... args) {
        if (args != null && args.length > 0) {
            message = String.format(message, args);
        }
        getToastManagerInstance().showToastInternal(toastIcon, duration, message);
    }
}
