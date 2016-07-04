package com.haiyangroup.scampus.common;

import android.app.Activity;
import android.content.Context;

import com.easemob.easeui.controller.EaseUI;
import com.haiyangroup.scampus.util.rximageloader.RxImageLoader;
import com.igexin.sdk.PushManager;

import java.util.LinkedList;
import java.util.List;

import compartment.ComponentCacheApplication;
/**
 * 应用初始化类
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class App extends ComponentCacheApplication {
    private AppComponent component;


    //运用list来保存们每一个activity是关键
    private List<Activity> mList = new LinkedList<Activity>();
    //为了实现每次使用该类时不创建新的对象而创建的静态对象
    private static App instance;
    //实例化一次
    public synchronized static App getInstance(){
        if (null == instance) {
            instance = new App();
        }
        return instance;
    }
    /**
     * 添加activity
     * @return void
     */
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    /**
     * 关闭某一个activity
     * @return void
     */
    public void finishActivity(String activityName) {
        for(Activity mActivity:mList)
        {
            if(activityName.equals(mActivity.getLocalClassName())){
                mActivity.finish();
            }
        }
    }

    /**
     * 关闭每一个list内的activity
     * @return void
     */
    public void exit() {
        try {
            for (Activity activity:mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    //杀进程
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    protected AppModule getApplicationModule() {
        return new AppModule(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EndObserver.init(getApplicationContext()); //统一观察者初始化
       //CrashHandler.getInstance().init(getApplicationContext());
        RxImageLoader.init(getApplicationContext());//异步图片加载库初始化
        EaseUI.getInstance().init(this);//环信聊天初始化
        PushManager.getInstance().initialize(this.getApplicationContext());//个推初始化

//        EMChat.getInstance().init(getApplicationContext());
//        EMChat.getInstance().setDebugMode(true);//在做打包混淆时，要关闭debug模式，避免消耗不必要的资源
//        EMChat.getInstance().setAutoLogin(true);
//
//        //开启测试模式
//        //  BeeCloud.setSandbox(true);
////此处第二个参数是控制台的test secret
//        BeeCloud.setAppIdAndSecret("b21dacc5-2294-4f1b-bbbd-58e14376da6a",
//                "59a764d5-f1fa-43c9-a27f-7a466cd652d7");
    }

    /**
     * 初始化component配置
     * @return void
     */
    public static AppComponent getAppComponent(Context context) {
        App app = (App)context.getApplicationContext();
        if (app.component == null) {
            app.component = DaggerAppComponent.builder()
                    .appModule(app.getApplicationModule())
                    .build();
        }
        return app.component;
    }

    public static void clearAppComponent(Context context) {
        App app = (App)context.getApplicationContext();
        app.component = null;
    }
}
