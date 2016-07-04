package com.haiyangroup.library.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

public class NotificationUtil {

    public static void notification(Context mContext,int icon, CharSequence tickerText ,CharSequence contentTitle, CharSequence contentText,Class<?> cls ){
        NotificationManager mNotificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(mContext,  cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Builder builder = new NotificationCompat.Builder(mContext);
//				new Notification(icon, tickerText, System.currentTimeMillis());  
        builder.setSmallIcon(icon).setTicker(tickerText)
                .setContentTitle(contentTitle).setContentText(contentText)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent);
        Notification notification=builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
//		notification.setLatestEventInfo(mContext,contentTitle, contentText, contentIntent);
        mNotificationManager.notify(0, notification);
    }
    public static void notification2(Context mContext,int icon, CharSequence tickerText ,CharSequence contentTitle, CharSequence contentText,Class<?> cls ){
        NotificationManager mNotificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(mContext,  cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Builder builder = new NotificationCompat.Builder(mContext);
//				new Notification(icon, tickerText, System.currentTimeMillis());

        builder.setSmallIcon(icon).setTicker(tickerText)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent);
        Notification notification=builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        notification.sound= Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.money);
//		notification.setLatestEventInfo(mContext,contentTitle, contentText, contentIntent);
        mNotificationManager.notify(0, notification);
    }
}
