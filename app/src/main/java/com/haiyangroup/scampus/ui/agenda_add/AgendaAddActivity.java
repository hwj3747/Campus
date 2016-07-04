package com.haiyangroup.scampus.ui.agenda_add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.entity.AgendaContentEntitty;

/**
 * 天假日程activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class AgendaAddActivity extends BaseActivity {
    public static AgendaAddActivity instance;
    AgendaAddFragment agendaAddFragment=new AgendaAddFragment();

    public static void launch(Context context,AgendaContentEntitty agendaContentEntitty) {
        Bundle bundle=new Bundle();
        bundle.putParcelable("agenda",agendaContentEntitty);
        Intent intent = new Intent(context, AgendaAddActivity.class);
        if(agendaContentEntitty!=null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_agenda_add;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(AgendaAddActivity.this);
        instance=AgendaAddActivity.this;
//        mAppBar.setTitleText("Test");

        mAppBar.setTitleText("日程安排 ");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {
        agendaAddFragment.setArguments(intent.getExtras());
    }

    @Override
    protected void onInitFragment() {
        showContent(agendaAddFragment,R.id.agenda_add);
    }

}
