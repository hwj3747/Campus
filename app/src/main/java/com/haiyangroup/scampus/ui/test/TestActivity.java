package com.haiyangroup.scampus.ui.test;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 测试activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class TestActivity extends BaseActivity {
    public static TestActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(TestActivity.this);
        instance=TestActivity.this;
//        mAppBar.setTitleText("Test");

        mAppBar.setTitleText("test");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new TestFragment(),R.id.test);
    }

}
