package com.haiyangroup.scampus.ui.card_consumption;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 校园卡消费信息activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class CardConsumptionActivity extends BaseActivity {
    public static CardConsumptionActivity instance;
    CardConsumptionFragment cardConsumptionFragment=new CardConsumptionFragment();
    static String type;
    public static void launch(Context context,String mType) {
        type=mType;
        Intent intent = new Intent(context, CardConsumptionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_card_consumption;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(CardConsumptionActivity.this);
        instance=CardConsumptionActivity.this;
//        mAppBar.setTitleText("Test");

        if(type.equals("month")) {
            mAppBar.setTitleText("最近三个月卡消费信息");
            Bundle bundle=new Bundle();
            bundle.putString("type",type);
            cardConsumptionFragment.setArguments(bundle);
        }
        else{
            mAppBar.setTitleText("最近一年卡充值信息");
            Bundle bundle=new Bundle();
            bundle.putString("type",type);
            cardConsumptionFragment.setArguments(bundle);
        }
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(cardConsumptionFragment,R.id.card_consumption);
    }

}
