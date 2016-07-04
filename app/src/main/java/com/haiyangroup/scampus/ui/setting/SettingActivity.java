package com.haiyangroup.scampus.ui.setting;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 设置页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class SettingActivity extends BaseActivity {

    public static SettingActivity instance;
    public static void launch(Context context) {

        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onInitTitle() {
        mAppBar.setTitleText("设置");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
        App.getInstance().addActivity(SettingActivity.this);
        instance=SettingActivity.this;
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new SettingFragment(),R.id.setting);
    }

}
