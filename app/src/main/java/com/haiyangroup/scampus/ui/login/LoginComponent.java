package com.haiyangroup.scampus.ui.login;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.LoginModel;
import com.haiyangroup.scampus.present.LoginPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 登录页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = LoginModel.class
)
@MyScope
public interface LoginComponent extends HasPresenter<LoginPresenter> {
    void inject(LoginFragment fragment);
}
