package com.haiyangroup.scampus.ui.filecabinet;

import android.content.Context;
import android.content.Intent;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;

/**
 * 个人文件柜activity
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class FileCabinetActivity extends BaseActivity {
    public static FileCabinetActivity instance;
    public static void launch(Context context) {
        Intent intent = new Intent(context, FileCabinetActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_file_cabinet;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(FileCabinetActivity.this);
        instance=FileCabinetActivity.this;
//        mAppBar.setTitleText("Test");

        mAppBar.setTitleText("个人文件柜");
        mAppBar.canFinishActivity();
        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
    }

    @Override
    protected void onResolveIntent(Intent intent) {

    }

    @Override
    protected void onInitFragment() {
        showContent(new FileCabinetFragment(),R.id.file_cabinet);
    }

}
