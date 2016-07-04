package com.haiyangroup.scampus.present;

import android.widget.Toast;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.NoteEntity;
import com.haiyangroup.scampus.model.ApprovalModel;
import com.haiyangroup.scampus.view.ApprovalView;

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
public class ApprovalPresenter extends BasePresenter<ApprovalView> {
    @Inject
    ApprovalModel model;

    @Inject
    ApprovalPresenter() {
    }

    private Subscription mApprovalFalseSubscription;

    private Subscription mApprovalTrueSubscription;

    public void getLeaveFalse(int index){
        if (mApprovalFalseSubscription != null && !mApprovalFalseSubscription.isUnsubscribed()) {
            return;
        }
        mApprovalFalseSubscription=model.getLeaveFalse(index).subscribe(GetApprovalObserver);
    }

    public void getLeaveTrue(int index){
        if (mApprovalFalseSubscription != null && !mApprovalFalseSubscription.isUnsubscribed()) {
            return;
        }
        mApprovalTrueSubscription=model.getLeaveTrue(index).subscribe(GetApprovalObserver);
    }

    public void LeaveApproval(String id,String deal){
        model.LeaveApproval(id, deal).subscribe(ApprovalObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mApprovalFalseSubscription != null && !mApprovalFalseSubscription.isUnsubscribed()) {
            mApprovalFalseSubscription.unsubscribe();
        }
        if (mApprovalTrueSubscription != null && !mApprovalTrueSubscription.isUnsubscribed()) {
            mApprovalTrueSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<NoteEntity>>> GetApprovalObserver = new EndObserver<AbsReturn<ArrayList<NoteEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<NoteEntity>> entity) {
            getView().showNote(entity.getData());
            getBaseView().hideLoading();
        }
    };


    private Observer<AbsReturn<DefaultData>> ApprovalObserver = new EndObserver<AbsReturn<DefaultData>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<DefaultData> entity) {
            if(entity.getCode()==1) {
                Toast.makeText(getBaseView().getBaseContext(), "提交成功", Toast.LENGTH_SHORT).show();
                getView().refresh();
            }
            getBaseView().hideLoading();
        }
    };
}
