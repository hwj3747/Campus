package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.AttendanceEntity;
import com.haiyangroup.scampus.model.AttendanceModel;
import com.haiyangroup.scampus.view.AttendanceView;

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
public class AttendancePresenter extends BasePresenter<AttendanceView> {
    @Inject
    AttendanceModel model;

    @Inject
    AttendancePresenter() {
    }

    private Subscription mAttendanceSubscription;

    public void getAttendance(String weekNum){
        mAttendanceSubscription=model.getAttendance(weekNum).subscribe(AttendanceObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAttendanceSubscription != null && !mAttendanceSubscription.isUnsubscribed()) {
            mAttendanceSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<AttendanceEntity>>> AttendanceObserver = new EndObserver<AbsReturn<ArrayList<AttendanceEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<AttendanceEntity>> entity) {
            getView().showAttendance(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
