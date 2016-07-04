package com.haiyangroup.scampus.ui.attendanceinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 学生考勤详情页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class AttendanceInfoActivity extends BaseActivity {
    AttendanceInfoFragment attendanceInfoFragment=new AttendanceInfoFragment();
    public static AttendanceInfoActivity instance;
    static String title;
    public static void launch(Context context,String mTitle,String week,String id) {
        title=mTitle;
        Bundle bundle=new Bundle();
        bundle.putString("week",week);
        bundle.putString("id",id);
        Intent intent = new Intent(context, AttendanceInfoActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_attendance_info;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(AttendanceInfoActivity.this);
        instance=AttendanceInfoActivity.this;
//        mAppBar.setTitleText("attendance_info");

        mAppBar.setTitleText(title);
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {
        attendanceInfoFragment.setArguments(intent.getExtras());
    }

    @Override
    protected void onInitFragment() {
        showContent(attendanceInfoFragment,R.id.attendance_info);
    }

}
