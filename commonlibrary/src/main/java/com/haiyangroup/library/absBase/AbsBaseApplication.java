package com.haiyangroup.library.absBase;

import android.app.Application;

import com.haiyangroup.library.log.LogUtils;
import com.haiyangroup.library.utils.CrashHandler;
import com.haiyangroup.library.utils.common.EnvironmentUtils;

public abstract class AbsBaseApplication extends Application {

    protected static AbsBaseApplication mBaseApplication;

    protected static AbsBaseApplication getInstance() {
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        EnvironmentUtils.init(this);
        CrashHandler.init(this);
        LogUtils.init(this);
        //注册分享
//        ShareSDK.initSDK(this);
    }

}
