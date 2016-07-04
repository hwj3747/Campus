package com.haiyangroup.scampus.present;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.ScheduleEntity;
import com.haiyangroup.scampus.model.ScheduleModel;
import com.haiyangroup.scampus.view.ScheduleView;

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
public class SchedulePresenter extends BasePresenter<ScheduleView> {
    @Inject
    ScheduleModel model;

    @Inject
    SchedulePresenter() {
    }

    private Subscription mScheduleSubscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mScheduleSubscription != null && !mScheduleSubscription.isUnsubscribed()) {
            mScheduleSubscription.unsubscribe();
        }

    }

    public void getSchedule(String weekNum){
        mScheduleSubscription=model.getSchedule(weekNum).subscribe(ScheduleObserver);
    }

    private Observer<AbsReturn<ArrayList<ScheduleEntity>>> ScheduleObserver = new EndObserver<AbsReturn<ArrayList<ScheduleEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<ScheduleEntity>> entity) {
            getView().showData(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
