package com.haiyangroup.scampus.ui.changepassword;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 更改密码页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class ChangePasswordActivity extends BaseActivity {

    public static ChangePasswordActivity instance;
    public static void launch(Context context) {

        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void onInitTitle() {
        mAppBar.setTitleText("修改密码");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
        App.getInstance().addActivity(ChangePasswordActivity.this);
        instance=ChangePasswordActivity.this;
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new ChangePasswordFragment(),R.id.change_password);
    }

}
