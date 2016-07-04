package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.ScoreEntity;
import com.haiyangroup.scampus.model.ScoreResultModel;
import com.haiyangroup.scampus.view.ScoreResultView;

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
public class ScoreResultPresenter extends BasePresenter<ScoreResultView> {
    @Inject
    ScoreResultModel model;

    @Inject
    ScoreResultPresenter() {
    }

    private Subscription mScoreResultSubscription;

    public void getScore(String subject,String year,String term,String type,String className){
        mScoreResultSubscription=model.getScore(subject,year,term,type, className).subscribe(ScoreResultObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mScoreResultSubscription != null && !mScoreResultSubscription.isUnsubscribed()) {
            mScoreResultSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<ScoreEntity>>> ScoreResultObserver = new EndObserver<AbsReturn<ArrayList<ScoreEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<ScoreEntity>> entity) {
            getView().showScoreResult(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
