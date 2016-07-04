package com.haiyangroup.scampus.ui.calendar;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 校历页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class CalendarActivity extends BaseActivity {
    public static CalendarActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, CalendarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(CalendarActivity.this);
        instance=CalendarActivity.this;
//        mAppBar.setTitleText("calendar");

        mAppBar.setTitleText("校历");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new CalendarFragment(),R.id.calendar);
    }

}
