package com.easemob.easeuisimpledemo;

import android.app.Application;

import com.easemob.easeui.controller.EaseUI;

public class DemoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        EaseUI.getInstance().init(this);
    }
    
}
