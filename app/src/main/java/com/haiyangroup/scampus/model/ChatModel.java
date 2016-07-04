package com.haiyangroup.scampus.model;


import com.haiyangroup.scampus.common.SchedulerProvider;
import com.haiyangroup.scampus.data.AbsService;

import javax.inject.Inject;

import dagger.Module;

/**
 * 实现MVP设计模式的model，用来从服务端提取数据，并提供数据接口供present使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Module
public class ChatModel {
    private final SchedulerProvider schedulerProvider = SchedulerProvider.DEFAULT;
    private final AbsService mService = new AbsService();

    @Inject
    public ChatModel() {
    }
}