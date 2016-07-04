package com.haiyangroup.scampus.present;

import android.util.Log;
import android.widget.Toast;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.model.CalendarModel;
import com.haiyangroup.scampus.model.FeedbackModel;
import com.haiyangroup.scampus.ui.feedback.FeedbackActivity;
import com.haiyangroup.scampus.view.FeedbackView;

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
public class FeedbackPresenter extends BasePresenter<FeedbackView> {
    @Inject
    FeedbackModel model;



    @Inject
    FeedbackPresenter() {
    }

    private Subscription mFeedbackSubscription;



    public void feedBack(String content){
        mFeedbackSubscription=model.feedBack(content).subscribe(FeedbackObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFeedbackSubscription != null && !mFeedbackSubscription.isUnsubscribed()) {
            mFeedbackSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<DefaultData>> FeedbackObserver = new EndObserver<AbsReturn<DefaultData>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<DefaultData> entity) {
            if (entity.getCode()==1){
                Toast.makeText(getBaseView().getBaseContext(),"提交成功！",Toast.LENGTH_SHORT).show();
                FeedbackActivity.instance.finish();
            }
            getBaseView().hideLoading();
        }
    };
}
