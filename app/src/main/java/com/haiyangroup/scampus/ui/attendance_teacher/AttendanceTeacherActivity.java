package com.haiyangroup.scampus.ui.attendance_teacher;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 教师出勤activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class AttendanceTeacherActivity extends BaseActivity {
    public static AttendanceTeacherActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, AttendanceTeacherActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_attendance_teacher;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(AttendanceTeacherActivity.this);
        instance=AttendanceTeacherActivity.this;
//        mAppBar.setTitleText("Test");

        mAppBar.setTitleText("教师考勤");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new AttendanceTeacherFragment(),R.id.attendance_teacher);
    }

}
