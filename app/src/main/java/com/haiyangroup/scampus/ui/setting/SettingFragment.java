package com.haiyangroup.scampus.ui.setting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.present.SettingPresenter;
import com.haiyangroup.scampus.ui.about.AboutActivity;
import com.haiyangroup.scampus.ui.changepassword.ChangePasswordActivity;
import com.haiyangroup.scampus.ui.feedback.FeedbackActivity;
import com.haiyangroup.scampus.util.DataCleanManager;
import com.haiyangroup.scampus.util.dialog.CashDialog;
import com.haiyangroup.scampus.view.SettingView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import icepick.Icepick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 设置页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class SettingFragment extends BaseFragment<SettingView, SettingComponent, SettingPresenter> implements SettingView {

    @Inject
    SettingPresenter presenter;
    @InjectView(R.id.change_password)
    RelativeLayout changePassword;
    @InjectView(R.id.about)
    RelativeLayout about;
    @InjectView(R.id.response)
    RelativeLayout response;
    @InjectView(R.id.clear)
    RelativeLayout clear;
    @InjectView(R.id.exit)
    Button exit;
    @InjectView(R.id.cash)
    TextView cash;
    @InjectView(R.id.setting)
    LinearLayout setting;

    public SettingFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_setting;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.setting);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        try {
            String cach = DataCleanManager.getCacheSize(getBaseContext().getCacheDir());
            cash.setText(cach);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onViewInit() {

    }

    @Override
    protected SettingComponent onCreateNonConfigurationComponent() {
        return DaggerSettingComponent.builder()
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

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    hideLoading();
                    try {
                        String cach = DataCleanManager.getCacheSize(getBaseContext().getCacheDir());
                        cash.setText(cach);
                        new CashDialog(getBaseContext()).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @OnClick({R.id.change_password, R.id.about, R.id.response, R.id.clear, R.id.exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_password:
                ChangePasswordActivity.launch(getBaseContext());
                break;
            case R.id.about:
                AboutActivity.launch(getBaseContext());
                break;
            case R.id.response:
                FeedbackActivity.launch(getBaseContext());
                break;
            case R.id.clear:

                showLoading("正在清理缓存");
//                Thread thread = new Thread(() -> {
//                    DataCleanManager.cleanApplicationData(getBaseContext());
//                    // TODO Auto-generated method stub
//                    Message message = new Message();
//                    message.what = 1;
//                    mHandler.sendMessage(message);
//                });
//                thread.start();

                Observable.create(subscriber -> {
                            DataCleanManager.cleanApplicationData(getBaseContext());
                            // TODO Auto-generated method stub
//                            Message message = new Message();
//                            message.what = 1;
//                            mHandler.sendMessage(message);
                            subscriber.onNext(true);
                            subscriber.onCompleted();
                        }
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(flag -> {
                            hideLoading();
                            try {
                                String cach = DataCleanManager.getCacheSize(getBaseContext().getCacheDir());
                                cash.setText(cach);
                                new CashDialog(getBaseContext()).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                break;
            case R.id.exit:
                App.getInstance().exit();
                break;
        }
    }
}
