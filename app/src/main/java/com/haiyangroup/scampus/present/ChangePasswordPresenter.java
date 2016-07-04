package com.haiyangroup.scampus.present;

import android.widget.Toast;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.model.ChangePasswordModel;
import com.haiyangroup.scampus.ui.changepassword.ChangePasswordActivity;
import com.haiyangroup.scampus.view.ChangePasswordView;

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
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordView> {
    @Inject
    ChangePasswordModel model;

    @Inject
    ChangePasswordPresenter() {
    }

    private Subscription mChangePasswordSubscription;

    public void modifyPassword(String oldPassword,String newPassword){
        mChangePasswordSubscription=model.modifyPassword(oldPassword, newPassword).subscribe(ChangePasswordObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChangePasswordSubscription != null && !mChangePasswordSubscription.isUnsubscribed()) {
            mChangePasswordSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<DefaultData>> ChangePasswordObserver = new EndObserver<AbsReturn<DefaultData>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<DefaultData> entity) {
            Toast.makeText(getBaseView().getBaseContext(), entity.getMessage(), Toast.LENGTH_SHORT).show();
            if(entity.getCode()==1)
                ChangePasswordActivity.instance.finish();
            getBaseView().hideLoading();
        }
    };
}
