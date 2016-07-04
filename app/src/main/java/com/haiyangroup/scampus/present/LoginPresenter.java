package com.haiyangroup.scampus.present;

import android.util.Log;
import android.widget.Toast;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.CurrentWeekEntity;
import com.haiyangroup.scampus.entity.LoginReturnEntity;
import com.haiyangroup.scampus.model.CalendarModel;
import com.haiyangroup.scampus.model.LoginModel;
import com.haiyangroup.scampus.ui.main.MainActivity;
import com.haiyangroup.scampus.view.LoginView;

import javax.inject.Inject;

import compartment.BasePresenter;
import rx.Observer;
import rx.Subscription;

/**
 * 实现MVP设计模式的present指挥者，用来控制model与view的交互
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@MyScope
public class LoginPresenter extends BasePresenter<LoginView> {
    @Inject
    LoginModel model;

    @Inject
    LoginPresenter() {
    }

    private Subscription mLoginSubscription;

    public void getWeek(String session){
        model.getWeek(session).subscribe(WeekObserver);
    }
    public void login(String username,String password){
        mLoginSubscription=model.login(username, password).subscribe(LoginObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLoginSubscription != null && !mLoginSubscription.isUnsubscribed()) {
            mLoginSubscription.unsubscribe();
        }

    }


    private Observer<LoginReturnEntity> LoginObserver = new EndObserver<LoginReturnEntity>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(LoginReturnEntity entity) {
            if(entity.getSessionid()!=null) {
                getWeek(entity.getSessionid());
                SharedPreferencesUtil.getInstance(getBaseView().getBaseContext()).putString("session", entity.getSessionid());
                MainActivity.launch(getBaseView().getBaseContext());
            }
            else{
                Toast.makeText(getBaseView().getBaseContext(),entity.getMessage(), Toast.LENGTH_SHORT).show();
            }
            getBaseView().hideLoading();
        }
    };


    private Observer<AbsReturn<CurrentWeekEntity>> WeekObserver = new EndObserver<AbsReturn<CurrentWeekEntity>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<CurrentWeekEntity> entity) {
            SharedPreferencesUtil.getInstance(getBaseView().getBaseContext()).putString("week", entity.getData().getWeek());
            getBaseView().hideLoading();
        }
    };
}
