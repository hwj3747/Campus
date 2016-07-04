package com.haiyangroup.scampus.ui.attendance;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.util.DensityUtil;
import com.haiyangroup.scampus.util.dialog.OneNumberPickerDialog;
import com.haiyangroup.scampus.util.dialog.PickListen;

/**
 * 学生考勤页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class AttendanceActivity extends BaseActivity {
    String week="第"+ SharedPreferencesUtil.getInstance(getBaseContext()).getString("week")+"周";
    AttendanceFragment attendanceFragment=new AttendanceFragment();

    public static AttendanceActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, AttendanceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_attendance;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(AttendanceActivity.this);
        instance=AttendanceActivity.this;
//        mAppBar.setTitleText("attendance");

        mAppBar.removeAllViews();
        mAppBar.initTitle(AttendanceActivity.this);
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
        mAppBar.setTitleText(week + " ");
        Drawable drawable= getResources().getDrawable(R.drawable.icon_tabbar_arrow2);
/// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, DensityUtil.dip2px(getBaseContext(), 14), DensityUtil.dip2px(getBaseContext(), 7));
        mAppBar.getmTitleView().setCompoundDrawables(null, null, drawable, null);
        PickListen pickListen=new PickListen() {
            @Override
            public void show(String text) {
                mAppBar.getmTitleView().setText(text+" ");
                week=text;
                attendanceFragment.setWeek(week);
            }
        };
        mAppBar.getmTitleView().setOnClickListener(v -> {
            new OneNumberPickerDialog(AttendanceActivity.this, getResources().getStringArray(R.array.week), pickListen).showDialog();
        });

    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(attendanceFragment,R.id.attendance);
    }

}
