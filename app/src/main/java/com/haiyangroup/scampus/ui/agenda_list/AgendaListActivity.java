package com.haiyangroup.scampus.ui.agenda_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.ui.agenda_add.AgendaAddActivity;

import butterknife.ButterKnife;

/**
 * 日程列表activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class AgendaListActivity extends BaseActivity {
    public static AgendaListActivity instance;
    AgendaListFragment agendaListFragment=new AgendaListFragment();
    private Boolean flag=false;
    RelativeLayout addAgenda;
    public static void launch(Context context) {
        Intent intent = new Intent(context, AgendaListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        addAgenda= (RelativeLayout) findViewById(R.id.add_agenda);
        addAgenda.setOnClickListener(v -> {
            AgendaAddActivity.launch(AgendaListActivity.this,null);});
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_agenda_list;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(AgendaListActivity.this);
        instance=AgendaListActivity.this;
//        mAppBar.setTitleText("Test");

        mAppBar.setTitleText("日程安排");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);

        mAppBar.addMenu(AgendaListActivity.this, R.drawable.dustbin);
        mAppBar.getMenu(1).setOnClickListener(v -> {
            if(flag) {
                agendaListFragment.hideDelete();
                flag=false;
            }
            else{
                agendaListFragment.showDelete();
                flag=true;
            }
        });
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(agendaListFragment,R.id.agenda_list);
    }

}
