package com.haiyangroup.scampus.ui.owner;


import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.OwnerModel;
import com.haiyangroup.scampus.present.OwnerPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 个人中心页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = OwnerModel.class
)
@MyScope
public interface OwnerComponent extends HasPresenter<OwnerPresenter> {
    void inject(OwnerFragment fragment);
}
