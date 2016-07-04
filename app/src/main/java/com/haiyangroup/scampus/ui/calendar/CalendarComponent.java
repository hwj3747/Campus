package com.haiyangroup.scampus.ui.calendar;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.CalendarModel;
import com.haiyangroup.scampus.present.CalendarPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 校历页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = CalendarModel.class
)
@MyScope
public interface CalendarComponent extends HasPresenter<CalendarPresenter> {
    void inject(CalendarFragment fragment);
}
