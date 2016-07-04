package com.haiyangroup.scampus.ui.news;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 学校新闻页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class NewsActivity extends BaseActivity {
    public static NewsActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, NewsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_news;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(NewsActivity.this);
        instance=NewsActivity.this;

        mAppBar.setTitleText("学校新闻");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new NewsFragment(),R.id.news);
    }

}
