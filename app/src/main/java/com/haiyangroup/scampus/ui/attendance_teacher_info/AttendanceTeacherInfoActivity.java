package com.haiyangroup.scampus.ui.attendance_teacher_info;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 教师出勤详情activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class AttendanceTeacherInfoActivity extends BaseActivity {
    public static AttendanceTeacherInfoActivity instance;

    static String titleName;
    public static void launch(Context context,String mTitleName) {
        titleName=mTitleName;
        Intent intent = new Intent(context, AttendanceTeacherInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_attendance_teacher_info;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(AttendanceTeacherInfoActivity.this);
        instance=AttendanceTeacherInfoActivity.this;
//        mAppBar.setTitleText("Test");

        mAppBar.setTitleText(titleName);
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new AttendanceTeacherInfoFragment(),R.id.attendance_teacher_info);
    }

}
