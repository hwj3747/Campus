package com.haiyangroup.scampus.model;


import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.common.CommonConfig;
import com.haiyangroup.scampus.common.SchedulerProvider;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.AbsService;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.NoteEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import rx.Observable;

/**
 * 实现MVP设计模式的model，用来从服务端提取数据，并提供数据接口供present使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Module
public class ApprovalModel {
    private final SchedulerProvider schedulerProvider = SchedulerProvider.DEFAULT;
    private final AbsService mService = new AbsService();

    @Inject
    public ApprovalModel() {
    }

    public Observable<AbsReturn<ArrayList<NoteEntity>>> getLeaveFalse(int index){
        return mService.getApi().getLeaveFalse(index, CommonConfig.pageSize, SharedPreferencesUtil.getInstance(App.getInstance()).getString("session")).compose(schedulerProvider.applySchedulers());
    }

    public Observable<AbsReturn<ArrayList<NoteEntity>>> getLeaveTrue(int index){
        return mService.getApi().getLeaveTrue(index, CommonConfig.pageSize, SharedPreferencesUtil.getInstance(App.getInstance()).getString("session")).compose(schedulerProvider.applySchedulers());
    }

    public Observable<AbsReturn<DefaultData>> LeaveApproval(String id,String deal){
        return mService.getApi().LeaveApproval(id, deal, SharedPreferencesUtil.getInstance(App.getInstance()).getString("session")).compose(schedulerProvider.applySchedulers());
    }
}