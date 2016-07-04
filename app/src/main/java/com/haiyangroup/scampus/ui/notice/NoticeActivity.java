package com.haiyangroup.scampus.ui.notice;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 公告通知页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class NoticeActivity extends BaseActivity {
    public static NoticeActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, NoticeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_notice;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(NoticeActivity.this);
        instance=NoticeActivity.this;
//        mAppBar.setTitleText("notice");

        mAppBar.setTitleText("公告通知");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new NoticeFragment(),R.id.notice);
    }

}
