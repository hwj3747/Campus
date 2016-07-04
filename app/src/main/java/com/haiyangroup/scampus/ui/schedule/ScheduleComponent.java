package com.haiyangroup.scampus.ui.schedule;


import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.ScheduleModel;
import com.haiyangroup.scampus.present.SchedulePresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 课表页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = ScheduleModel.class
)
@MyScope
public interface ScheduleComponent extends HasPresenter<SchedulePresenter> {
    void inject(ScheduleFragment fragment);
}
