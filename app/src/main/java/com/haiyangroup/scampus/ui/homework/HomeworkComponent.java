package com.haiyangroup.scampus.ui.homework;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.HomeworkModel;
import com.haiyangroup.scampus.present.HomeworkPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 布置作业页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = HomeworkModel.class
)
@MyScope
public interface HomeworkComponent extends HasPresenter<HomeworkPresenter> {
    void inject(HomeworkFragment fragment);
}
