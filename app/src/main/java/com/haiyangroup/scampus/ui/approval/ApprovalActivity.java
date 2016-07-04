package com.haiyangroup.scampus.ui.approval;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 请假审批页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class ApprovalActivity extends BaseActivity {
    public static ApprovalActivity instance;
    ApprovalFragment approvalFragment=new ApprovalFragment();
    public static void launch(Context context) {
        Intent intent = new Intent(context, ApprovalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_approval;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(ApprovalActivity.this);
        instance=ApprovalActivity.this;
//        mAppBar.setTitleText("approval");

        mAppBar.setTitleText("请假审批");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(approvalFragment,R.id.approval);
    }
}
