package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.model.CardModel;
import com.haiyangroup.scampus.view.CardView;

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
public class CardPresenter extends BasePresenter<CardView> {
    @Inject
    CardModel model;

    @Inject
    CardPresenter() {
    }

    private Subscription mCardSubscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCardSubscription != null && !mCardSubscription.isUnsubscribed()) {
            mCardSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<DefaultData>> CardObserver = new EndObserver<AbsReturn<DefaultData>>() {
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
