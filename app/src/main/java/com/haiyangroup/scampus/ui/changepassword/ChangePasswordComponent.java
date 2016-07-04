package com.haiyangroup.scampus.ui.changepassword;


import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.ChangePasswordModel;
import com.haiyangroup.scampus.present.ChangePasswordPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 更改密码页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = ChangePasswordModel.class
)
@MyScope
public interface ChangePasswordComponent extends HasPresenter<ChangePasswordPresenter> {
    void inject(ChangePasswordFragment fragment);
}
