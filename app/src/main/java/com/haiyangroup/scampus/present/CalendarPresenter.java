package com.haiyangroup.scampus.present;

import android.util.Log;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.CalendarEntity;
import com.haiyangroup.scampus.model.CalendarModel;
import com.haiyangroup.scampus.view.CalendarView;

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
public class CalendarPresenter extends BasePresenter<CalendarView> {
    @Inject
    CalendarModel model;

    @Inject
    CalendarPresenter() {
    }

    private Subscription mCalendarSubscription;

    public void init(){
        mCalendarSubscription=model.getCalendar().subscribe(CalendarObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCalendarSubscription != null && !mCalendarSubscription.isUnsubscribed()) {
            mCalendarSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<CalendarEntity>>> CalendarObserver = new EndObserver<AbsReturn<ArrayList<CalendarEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<CalendarEntity>> entity) {
            getView().showData(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
