package com.haiyangroup.scampus.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.ui.contacts.ContactsFragment;
import com.haiyangroup.scampus.ui.home.HomeFragment;
import com.haiyangroup.scampus.ui.message.MessageFragment;
import com.haiyangroup.scampus.ui.owner.OwnerFragment;
import com.haiyangroup.scampus.ui.schedule.ScheduleFragment;
import com.haiyangroup.scampus.ui.setting.SettingActivity;
import com.haiyangroup.scampus.util.DensityUtil;
import com.haiyangroup.scampus.util.dialog.OneNumberPickerDialog;
import com.haiyangroup.scampus.util.dialog.PickListen;

import butterknife.ButterKnife;
/**
 * 主页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    public static MainActivity instance;
    RadioGroup bottomRg;
    String week="第"+ SharedPreferencesUtil.getInstance(getBaseContext()).getString("week")+"周";
    private long exitTime = 0;
    private HomeFragment mHomeFragment;//首页5个菜单栏
    private ScheduleFragment mScheduleFragment;
    private ContactsFragment mContactsFragment;
    private MessageFragment mMessageFragment;
    private OwnerFragment mOwnerFragment;


    public MainActivity() {
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitTitle() {
        App.getInstance().addActivity(MainActivity.this);
        instance=MainActivity.this;
        mAppBar.setNavigationOnClickListener(v -> {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                App.getInstance().exit();
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        bottomRg= (RadioGroup) findViewById(R.id.main_tab_group);
        bottomRg.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onResolveIntent(Intent intent) {
    }

    @Override
    protected void onInitFragment() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        bottomRg= (RadioGroup) findViewById(R.id.main_tab_group);
        bottomRg.setOnCheckedChangeListener(this);
        showHomeFragmnet();//默认显示首页
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.menu1:
                showHomeFragmnet();
                break;

            case R.id.menu2:
                showScheduleFragment();
                break;

            case R.id.menu3:
                showContactsFragment();
                break;

            case R.id.menu4:
                showMessageFragment();
                break;
            case R.id.menu5:
               showOwnerFragment();
                break;
            default:
                break;
        }
    }

    private void showOwnerFragment() {//我的资料
        if (null == mOwnerFragment) {
            mOwnerFragment = new OwnerFragment();
        }
        mAppBar.removeAllViews();
        mAppBar.initTitle(MainActivity.this);
        mAppBar.setTitleText("我的资料");
        mAppBar.addMenu(this, R.string.setting);
            mAppBar.getMenu(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SettingActivity.launch(MainActivity.this);
                }
            });
        ((TextView)mAppBar.getMenu(1)).setTextColor(getResources().getColor(R.color.white));
        showMainContent(mOwnerFragment, R.id.fl_content);
    }

    private void showMessageFragment() {//消息
        if (null == mMessageFragment) {
            mMessageFragment = new MessageFragment();
        }
        mAppBar.removeAllViews();
        mAppBar.initTitle(MainActivity.this);
        mAppBar.setTitleText("消息");
        showMainContent(mMessageFragment,R.id.fl_content);
    }

    private void showContactsFragment() {//联系人
        if (null == mContactsFragment) {
            mContactsFragment = new ContactsFragment();
        }
        mAppBar.removeAllViews();
        mAppBar.initTitle(MainActivity.this);
        mAppBar.setTitleText("联系人");
        showMainContent(mContactsFragment,R.id.fl_content);

    }

    private void showScheduleFragment() {//课表
        if (null == mScheduleFragment) {
            mScheduleFragment = new ScheduleFragment();
        }
        mAppBar.removeAllViews();
        mAppBar.initTitle(MainActivity.this);
        mAppBar.setTitleText(week+" ");
        Drawable drawable= getResources().getDrawable(R.drawable.icon_tabbar_arrow2);
/// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, DensityUtil.dip2px(getBaseContext(), 14), DensityUtil.dip2px(getBaseContext(), 7));
        mAppBar.getmTitleView().setCompoundDrawables(null, null, drawable, null);
        PickListen pickListen=new PickListen() {
            @Override
            public void show(String text) {
                mAppBar.getmTitleView().setText(text+" ");
                week=text;
                mScheduleFragment.setWeek(week);
            }
        };
        mAppBar.getmTitleView().setOnClickListener(v -> {
            new OneNumberPickerDialog(MainActivity.this, getResources().getStringArray(R.array.week), pickListen).showDialog();
        });

//        ImageView img=new ImageView(mAppBar.getContext());
//        img.setImageResource(R.drawable.icon_tabbar_arrow2);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dip2px(getBaseContext(), 5), DensityUtil.dip2px(getBaseContext(), 10));
//        img.setLayoutParams(lp);//设置布局参数
//        Toolbar.LayoutParams params = new Toolbar.LayoutParams(DensityUtil.dip2px(getBaseContext(), 15),DensityUtil.dip2px(getBaseContext(), 30), Gravity.CENTER);
//        params.setMargins(0,0, DensityUtil.dip2px(getBaseContext(), 50),0);
//        mAppBar.addView(img,params);
       // mAppBar.getmTitleView()
//        Spinner spinner=new Spinner(mAppBar.getContext());
//        ArrayList<String> data_list = new ArrayList<String>();
//        data_list.add("第一周");
//        data_list.add("第二周");
//        data_list.add("第三周");
//        data_list.add("第四周");
//
//        //适配器
//        ArrayAdapter<String> arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data_list);
//        //设置样式
//        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器
//        spinner.setAdapter(arr_adapter);
//
//        spinner.setBackgroundColor(getResources().getColor(R.color.transparent));
//        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
//        params.setMargins(0,0, DensityUtil.dip2px(getBaseContext(), 50),0);
//        mAppBar.addView(spinner,params);
//        mAppBar.canFinishActivity();
//        mAppBar.setNavigationIcon(R.drawable.icon_tabbar_arrow);
        showMainContent(mScheduleFragment,R.id.fl_content);
    }

    public void showHomeFragmnet() {//首页
        if (null == mHomeFragment) {
            mHomeFragment = new HomeFragment();
        }
        mAppBar.removeAllViews();
        mAppBar.initTitle(MainActivity.this);
        mAppBar.setTitleText("首页");
        showMainContent(mHomeFragment,R.id.fl_content);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                App.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
