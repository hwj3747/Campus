package com.haiyangroup.scampus.ui.card;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 一卡通查询activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class CardActivity extends BaseActivity {
    public static CardActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, CardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_card;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(CardActivity.this);
        instance=CardActivity.this;
//        mAppBar.setTitleText("Test");

        mAppBar.setTitleText("一卡通查询");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new CardFragment(),R.id.card);
    }

}
