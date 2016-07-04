package com.haiyangroup.scampus.ui.about;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 关于我们页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AboutActivity extends BaseActivity {
    public static AboutActivity instance;

    /**
     * 设置从别的activity跳转到此activity的方法
     * @param context 前一个activity的context
     * @return void
     */
    public static void launch(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    /**
     * 设置此activity的对应的XML文件
     * 继承自基础activity，默认调用
     */
    @Override
    protected int getLayoutID() {
        return R.layout.activity_about;
    }

    /**
     * 设置从别的activity跳转到此activity的方法
     * 1、mAppbar 系统的标题栏，可以设置标题内容，返回图标等
     * 2、instance 得到本activity的实例
     */
    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(AboutActivity.this);
        instance=AboutActivity.this;

        mAppBar.setTitleText("关于我们");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    /**
     * 设置本activity要显示的fragment
     * 继承自基础activity，默认调用
     */
    @Override
    protected void onInitFragment() {
        showContent(new AboutFragment(),R.id.about);
    }

}
