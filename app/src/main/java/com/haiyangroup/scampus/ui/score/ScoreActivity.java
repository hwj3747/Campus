package com.haiyangroup.scampus.ui.score;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 成绩查询页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class ScoreActivity extends BaseActivity {
    public static ScoreActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, ScoreActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_score;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(ScoreActivity.this);
        instance=ScoreActivity.this;
//        mAppBar.setTitleText("score");

        mAppBar.setTitleText("成绩查询");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new ScoreFragment(),R.id.score);
    }

}
