package com.haiyangroup.scampus.present;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.CommonConfig;
import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.NoticeEntity;
import com.haiyangroup.scampus.model.NoticeModel;
import com.haiyangroup.scampus.view.NoticeView;

import java.util.ArrayList;

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
public class NoticePresenter extends BasePresenter<NoticeView> {
    @Inject
    NoticeModel model;

    @Inject
    NoticePresenter() {
    }

    private Subscription mNoticeSubscription;

    public void getNotice(int index){
        if (mNoticeSubscription != null && !mNoticeSubscription.isUnsubscribed()) {
            return;
        }
        mNoticeSubscription=model.getNotice(index, CommonConfig.pageSize).subscribe(NoticeObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mNoticeSubscription != null && !mNoticeSubscription.isUnsubscribed()) {
            mNoticeSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<NoticeEntity>>> NoticeObserver = new EndObserver<AbsReturn<ArrayList<NoticeEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<NoticeEntity>> entity) {
            getView().showNotice(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
