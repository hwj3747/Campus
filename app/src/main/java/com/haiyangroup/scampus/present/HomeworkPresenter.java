package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.HomeworkEntity;
import com.haiyangroup.scampus.model.HomeworkModel;
import com.haiyangroup.scampus.view.HomeworkView;

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
public class HomeworkPresenter extends BasePresenter<HomeworkView> {
    @Inject
    HomeworkModel model;

    @Inject
    HomeworkPresenter() {
    }

    private Subscription mHomeworkSubscription;

    public void getHomeWork(String weekNum){
        model.getHomeWork(weekNum).subscribe(HomeworkObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHomeworkSubscription != null && !mHomeworkSubscription.isUnsubscribed()) {
            mHomeworkSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<HomeworkEntity>>> HomeworkObserver = new EndObserver<AbsReturn<ArrayList<HomeworkEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<HomeworkEntity>> entity) {
            getView().showHomework(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
