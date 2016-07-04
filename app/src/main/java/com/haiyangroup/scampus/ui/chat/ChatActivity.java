package com.haiyangroup.scampus.ui.chat;

import android.content.Intent;
import android.os.Bundle;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.domain.EaseUser;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.ui.EaseChatFragment;
import com.haiyangroup.scampus.R;
/**
 * 聊天页面的activity实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */

public class ChatActivity extends EaseBaseActivity {
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_chat);

        EaseUI easeUI = EaseUI.getInstance();
//需要easeui库显示用户头像和昵称设置此provider
        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {

            @Override
            public EaseUser getUser(String username) {
                EaseUser easeUser =new EaseUser(username);
                if(username.equals("123456")) {
                    easeUser.setAvatar("http://w2.hoopchina.com.cn/fa/2f/03/fa2f0381b1e475decc79ed835bbd9f23001.jpg");
                    easeUser.setNick("哈哈");
                }
                else {
                    easeUser.setAvatar("http://w1.hoopchina.com.cn/47/29/a6/4729a6c09a74348613b07314457f0824001.jpg");
                    easeUser.setNick("呵呵");
                }
                return easeUser;
            }
        });

//        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
//
//            @Override
//            public EaseUser getUser(String username) {
//                EaseUser easeUser =new EaseUser("654321");
//                easeUser.setAvatar("http://w1.hoopchina.com.cn/47/29/a6/4729a6c09a74348613b07314457f0824001.jpg");
//                return easeUser;
//            }
//        });

        activityInstance = this;
        //聊天人或群id
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        chatFragment = new EaseChatFragment();

        //传入参数
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();


    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }
    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }
    
    public String getToChatUsername(){
        return toChatUsername;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
