package com.haiyangroup.scampus.ui.rollcall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 点名页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class RollcallActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    public static RollcallActivity instance;
    RadioGroup bottomRg;
    static String type;
    static String className;
    static String mId;
    RollcallFragment rollcallFragment = new RollcallFragment();
    HomeworkSetFragment homeworkSetFragment = new HomeworkSetFragment();

    public static void launch(Context context, String mType,String id, String mClassName) {
        type = mType;
        className = mClassName;
        mId=id;
        Intent intent = new Intent(context, RollcallActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_rollcall;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(RollcallActivity.this);
        instance = RollcallActivity.this;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bottomRg = (RadioGroup) findViewById(R.id.main_tab_group);
        bottomRg.setOnCheckedChangeListener(this);
        if (type.equals("rollcall")) {
            bottomRg.check(R.id.menu1);
            showRollcall();
        } else{
            bottomRg.setVisibility(View.GONE);
            showHomework();
        }
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.menu1:
                showRollcall();
                break;

            case R.id.menu2:
                showHomework();
                break;
            default:
                break;
        }
    }


    private void showRollcall() {
        if (null == rollcallFragment) {
            rollcallFragment = new RollcallFragment();
        }
        mAppBar.removeAllViews();
        mAppBar.initTitle(RollcallActivity.this);
        mAppBar.setTitleText("点名");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
        mAppBar.addMenu(RollcallActivity.this, R.string.save);
        mAppBar.getMenu(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ((TextView)mAppBar.getMenu(1)).setTextColor(getResources().getColor(R.color.white));
        showMainContent(rollcallFragment, R.id.fl_content);
    }

    private void showHomework() {
        if (null == homeworkSetFragment) {
            homeworkSetFragment = new HomeworkSetFragment();
        }
        mAppBar.removeAllViews();
        mAppBar.initTitle(RollcallActivity.this);
        mAppBar.setTitleText(className);
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
        mAppBar.addMenu(RollcallActivity.this, R.string.save);
        mAppBar.getMenu(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeworkSetFragment.saveHomework(mId);
            }
        });
        ((TextView)mAppBar.getMenu(1)).setTextColor(getResources().getColor(R.color.white));
        showMainContent(homeworkSetFragment, R.id.fl_content);
    }

}
