package com.haiyangroup.scampus.ui.scoreresult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 成绩查询结果页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class ScoreResultActivity extends BaseActivity {
    public static ScoreResultActivity instance;
    ScoreResultFragment scoreResultFragment=new ScoreResultFragment();
    public static void launch(Context context,String subject,String year,String term,String type,String className) {
        Bundle bundle=new Bundle();
        bundle.putString("subject",subject);
        bundle.putString("year",year);
        bundle.putString("term",term);
        bundle.putString("type",type);
        bundle.putString("className",className);
        Intent intent = new Intent(context, ScoreResultActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_score_result;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(ScoreResultActivity.this);
        instance=ScoreResultActivity.this;
//        mAppBar.setTitleText("score_result");

        mAppBar.setTitleText("查询结果");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {
        scoreResultFragment.setArguments(intent.getExtras());
    }

    @Override
    protected void onInitFragment() {
        showContent(scoreResultFragment,R.id.score_result);
    }

}
