package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.AttendanceInfoEntity;
import com.haiyangroup.scampus.model.AttendanceInfoModel;
import com.haiyangroup.scampus.view.AttendanceInfoView;

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
public class AttendanceInfoPresenter extends BasePresenter<AttendanceInfoView> {
    @Inject
    AttendanceInfoModel model;

    @Inject
    AttendanceInfoPresenter() {
    }

    private Subscription mAttendanceInfoSubscription;

    public void getAttendanceInfo(String weekNum,String id){
        mAttendanceInfoSubscription=model.getAttendanceInfo(weekNum, id).subscribe(AttendanceInfoObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAttendanceInfoSubscription != null && !mAttendanceInfoSubscription.isUnsubscribed()) {
            mAttendanceInfoSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<AttendanceInfoEntity>>> AttendanceInfoObserver = new EndObserver<AbsReturn<ArrayList<AttendanceInfoEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<AttendanceInfoEntity>> entity) {
            getView().showAttendanceInfo(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
