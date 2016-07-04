package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.model.FileCabinetModel;
import com.haiyangroup.scampus.view.FileCabinetView;

import javax.inject.Inject;

import compartment.BasePresenter;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * 实现MVP设计模式的present指挥者，用来控制model与view的交互
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@MyScope
public class FileCabinetPresenter extends BasePresenter<FileCabinetView> {
    @Inject
    FileCabinetModel model;

    @Inject
    FileCabinetPresenter() {
    }

    private Subscription mFileCabinetSubscription= Subscriptions.empty();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFileCabinetSubscription != null && !mFileCabinetSubscription.isUnsubscribed()) {
            mFileCabinetSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<DefaultData>> FileCabinetObserver = new EndObserver<AbsReturn<DefaultData>>() {
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
