package com.haiyangroup.scampus.ui.card_info;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 校园卡详情activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class CardInfoActivity extends BaseActivity {
    public static CardInfoActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, CardInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_card_info;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(CardInfoActivity.this);
        instance=CardInfoActivity.this;
//        mAppBar.setTitleText("Test");

        mAppBar.setTitleText("我的校园卡信息");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
        mAppBar.addMenu(CardInfoActivity.this, R.string.loss);
        ((TextView)mAppBar.getMenu(1)).setTextColor(getResources().getColor(R.color.white));
        mAppBar.getMenu(1).setOnClickListener(v->{});
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new CardInfoFragment(),R.id.card_info);
    }

}
