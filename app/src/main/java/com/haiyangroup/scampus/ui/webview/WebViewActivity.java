package com.haiyangroup.scampus.ui.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 网页浏览页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class WebViewActivity extends BaseActivity {
    public static WebViewActivity instance;
    static String mURL;
    static String mTitle;
    WebViewFragment webViewFragment=new WebViewFragment();
    public static void launch(Context context,String URL,String Title) {
        mTitle=Title;
        mURL=URL;
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(WebViewActivity.this);
        instance=WebViewActivity.this;

        mAppBar.setTitleText(mTitle);
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);

        mAppBar.setNavigationOnClickListener(v -> {
            webViewFragment.back();
        });
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        Bundle urlBundle=new Bundle();
        urlBundle.putString("url",mURL);
        webViewFragment.setArguments(urlBundle);
        showContent(webViewFragment, R.id.webview);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            webViewFragment.back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
