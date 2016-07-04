package com.haiyangroup.scampus.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.LoginPresenter;
import com.haiyangroup.scampus.ui.main.MainActivity;
import com.haiyangroup.scampus.view.LoginView;
import com.haiyangroup.library.utils.SharedPreferencesUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 登录页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class LoginFragment extends BaseFragment<LoginView, LoginComponent, LoginPresenter> implements LoginView {

    @Inject
    LoginPresenter presenter;
    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.btn_login)
    Button btnLogin;

    public LoginFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_login;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.login);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        Bundle bundle=getArguments();
        if (bundle != null) {
            String name = bundle.getString("username");
            String pwd = bundle.getString("password");
            if (name != null && pwd != null) {
                username.setText(name);
                password.setText(pwd);
                login();
            }
        }
    }

    @Override
    protected LoginComponent onCreateNonConfigurationComponent() {
        return DaggerLoginComponent.builder()
                .appComponent(getAppComponent(mActivity))
                .build();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save state of all @State annotated members
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btn_login)
    public void login(){
        presenter.login(username.getText().toString(),password.getText().toString());
//        if(EMChat.getInstance().isLoggedIn()){
//            //登录过直接进入主页面
//            MainActivity.launch(getBaseContext());
//        }
//        else if(username.getText().toString().equals("")||password.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "账号密码不能为空", Toast.LENGTH_SHORT).show();
//        }
//        else {
            SharedPreferencesUtil.getInstance(getBaseContext()).putString("touser",username.getText().toString().equals("123456")?"654321":"123456");
            //登录
            EMChatManager.getInstance().login("123456","123456", new EMCallBack() {

                @Override
                public void onSuccess() {
                   // MainActivity.launch(getBaseContext());
                }

                @Override
                public void onProgress(int progress, String status) {

                }

                @Override
                public void onError(int code, String error) {
                    ((Activity) getBaseContext()).runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
//        }
    }
}
