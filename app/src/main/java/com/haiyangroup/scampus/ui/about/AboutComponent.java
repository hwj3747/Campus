package com.haiyangroup.scampus.ui.about;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.AboutModel;
import com.haiyangroup.scampus.present.AboutPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 关于我们页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
//@Component(
//        dependencies = AppComponent.class, modules = {AboutModel.class,ApprovalModel.class}
//)
@Component(
        dependencies = AppComponent.class, modules =AboutModel.class
)
@MyScope
public interface AboutComponent extends HasPresenter<AboutPresenter> {
    void inject(AboutFragment fragment);
}
