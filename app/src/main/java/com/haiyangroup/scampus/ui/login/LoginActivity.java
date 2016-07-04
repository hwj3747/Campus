package com.haiyangroup.scampus.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 登录页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class LoginActivity extends BaseActivity {
    LoginFragment loginFragment=new LoginFragment();
    public static LoginActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String name = bundle.getString("username");
            String password = bundle.getString("password");
            if (name != null && password != null) {
                loginFragment.setArguments(bundle);
                Toast.makeText(getApplicationContext(), "username:" + name + "password:" + password, Toast.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(LoginActivity.this);
        instance=LoginActivity.this;
        mAppBar.removeAllViews();
//        mAppBar.setTitleText("login");
//
//        mAppBar.setTitleText("login");
//        mAppBar.canFinishActivity();
//        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(loginFragment,R.id.login);
    }

}
