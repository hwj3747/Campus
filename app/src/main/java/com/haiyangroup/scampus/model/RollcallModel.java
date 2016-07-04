package com.haiyangroup.scampus.model;


import android.util.Log;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.common.SchedulerProvider;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.AbsService;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.RollCallEntity;
import com.haiyangroup.scampus.entity.RollCallListForm;
import com.haiyangroup.scampus.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import rx.Observable;

/**
 * 实现MVP设计模式的model，用来从服务端提取数据，并提供数据接口供present使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Module
public class RollcallModel {
    private final SchedulerProvider schedulerProvider = SchedulerProvider.DEFAULT;
    private final AbsService mService = new AbsService();

    @Inject
    public RollcallModel() {
    }

    public Observable<AbsReturn<DefaultData>> saveHomework(String id,String content){
        return mService.getApi().saveHomeWork(id,content, SharedPreferencesUtil.getInstance(App.getInstance()).getString("session")).compose(schedulerProvider.applySchedulers());
    }

    public Observable<AbsReturn<DefaultData>> saveRollCall(){

        RollCallListForm rollCallListForm=new RollCallListForm();
        ArrayList<RollCallEntity> rollCallEntities=new ArrayList<>();
        RollCallEntity rollCallEntity=new RollCallEntity();
        rollCallEntity.setId("b12267b5ad964fbc9c7700c940926db7");
        rollCallEntity.setRollCall(44);
        rollCallEntities.add(rollCallEntity);
        rollCallListForm.setRollCallEntities(rollCallEntities);
        Log.i("json",GsonUtil.toJson(rollCallListForm));
        return mService.getApi().saveRollCall("3","1c1e510873b24b85b120cfe2bb6b93a2", GsonUtil.toJson(rollCallListForm),SharedPreferencesUtil.getInstance(App.getInstance()).getString("session")).compose(schedulerProvider.applySchedulers());
    }
}