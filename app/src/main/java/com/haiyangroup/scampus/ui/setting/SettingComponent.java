package com.haiyangroup.scampus.ui.setting;


import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.SettingModel;
import com.haiyangroup.scampus.present.SettingPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 设置页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = SettingModel.class
)
@MyScope
public interface SettingComponent extends HasPresenter<SettingPresenter> {
    void inject(SettingFragment fragment);
}
