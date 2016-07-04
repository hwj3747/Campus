package com.haiyangroup.scampus.ui.home;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.HomeModel;
import com.haiyangroup.scampus.present.HomePresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 首页页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = HomeModel.class
)
@MyScope
public interface HomeComponent extends HasPresenter<HomePresenter> {
    void inject(HomeFragment fragment);
}
