package com.haiyangroup.scampus.present;

import android.widget.Toast;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.model.RollcallModel;
import com.haiyangroup.scampus.ui.rollcall.RollcallActivity;
import com.haiyangroup.scampus.view.RollcallView;

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
public class RollcallPresenter extends BasePresenter<RollcallView> {
    @Inject
    RollcallModel model;

    @Inject
    RollcallPresenter() {
    }

    private Subscription mRollcallSubscription;

    public void saveHomework(String id,String content){
        model.saveRollCall().subscribe();
        model.saveHomework(id, content).subscribe(RollcallObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRollcallSubscription != null && !mRollcallSubscription.isUnsubscribed()) {
            mRollcallSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<DefaultData>> RollcallObserver = new EndObserver<AbsReturn<DefaultData>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<DefaultData> entity) {
            if(entity.getCode()==1){
                Toast.makeText(getBaseView().getBaseContext(),"提交成功",Toast.LENGTH_SHORT).show();
                RollcallActivity.instance.finish();
            }
            getBaseView().hideLoading();
        }
    };
}
