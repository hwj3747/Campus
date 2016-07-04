package com.haiyangroup.scampus.model;


import com.haiyangroup.scampus.common.SchedulerProvider;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.AbsService;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.CurrentWeekEntity;
import com.haiyangroup.scampus.entity.LoginReturnEntity;

import javax.inject.Inject;

import dagger.Module;
import rx.Observable;

/**
 * 实现MVP设计模式的model，用来从服务端提取数据，并提供数据接口供present使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Module
public class LoginModel {
    private final SchedulerProvider schedulerProvider = SchedulerProvider.DEFAULT;
    private final AbsService mService = new AbsService();

    @Inject
    public LoginModel() {
    }

    public Observable<LoginReturnEntity> login(String username,String password){
        return mService.getApi().Login(username,password,true).compose(schedulerProvider.applySchedulers());
    }

    public Observable<AbsReturn<CurrentWeekEntity>> getWeek(String session){
        return mService.getApi().getWeek(session).compose(schedulerProvider.applySchedulers());
    }
}