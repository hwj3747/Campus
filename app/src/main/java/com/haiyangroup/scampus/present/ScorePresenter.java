package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.ConditionResultEntity;
import com.haiyangroup.scampus.model.ScoreModel;
import com.haiyangroup.scampus.view.ScoreView;

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
public class ScorePresenter extends BasePresenter<ScoreView> {
    @Inject
    ScoreModel model;

    @Inject
    ScorePresenter() {
    }

    private Subscription mScoreSubscription;

    public void getCondition(){
        mScoreSubscription=model.getCondition().subscribe(ScoreObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mScoreSubscription != null && !mScoreSubscription.isUnsubscribed()) {
            mScoreSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ConditionResultEntity>> ScoreObserver = new EndObserver<AbsReturn<ConditionResultEntity>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ConditionResultEntity> entity) {
            getView().showCondition(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
