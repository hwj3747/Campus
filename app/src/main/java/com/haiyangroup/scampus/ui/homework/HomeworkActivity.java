package com.haiyangroup.scampus.ui.homework;

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
 * 布置作业页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class HomeworkActivity extends BaseActivity {
    String week="第"+ SharedPreferencesUtil.getInstance(getBaseContext()).getString("week")+"周";
    HomeworkFragment homeworkFragment =new HomeworkFragment();

    public static HomeworkActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, HomeworkActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_homework;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(HomeworkActivity.this);
        instance=HomeworkActivity.this;
//        mAppBar.setTitleText("homework");

        mAppBar.removeAllViews();
        mAppBar.initTitle(HomeworkActivity.this);
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
                homeworkFragment.setWeek(week);
            }
        };
        mAppBar.getmTitleView().setOnClickListener(v -> {
            new OneNumberPickerDialog(HomeworkActivity.this, getResources().getStringArray(R.array.week), pickListen).showDialog();
        });

    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(homeworkFragment,R.id.homework);
    }

}
