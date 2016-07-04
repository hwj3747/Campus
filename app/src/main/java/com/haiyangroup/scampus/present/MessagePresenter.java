package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.model.MessageModel;
import com.haiyangroup.scampus.view.MessageView;

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
public class MessagePresenter extends BasePresenter<MessageView> {
    @Inject
    MessageModel model;

    @Inject
    MessagePresenter() {
    }

    private Subscription mMessageSubscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMessageSubscription != null && !mMessageSubscription.isUnsubscribed()) {
            mMessageSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<DefaultData>> ContactsObserver = new EndObserver<AbsReturn<DefaultData>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<DefaultData> entity) {
            getBaseView().hideLoading();
        }
    };
}
