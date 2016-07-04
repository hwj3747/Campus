package com.haiyangroup.scampus.ui.feedback;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;
/**
 * 意见反馈页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class FeedbackActivity extends BaseActivity {
    public static FeedbackActivity instance;
    FeedbackFragment feedbackFragment=new FeedbackFragment();
    public static void launch(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(FeedbackActivity.this);
        instance=FeedbackActivity.this;
//        mAppBar.setTitleText("feedback");

        mAppBar.setTitleText("意见反馈");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);

        mAppBar.addMenu(this, R.string.submit);
        mAppBar.getMenu(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbackFragment.submit();
            }
        });
        ((TextView)mAppBar.getMenu(1)).setTextColor(getResources().getColor(R.color.white));

    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(feedbackFragment,R.id.feedback);
    }

}
