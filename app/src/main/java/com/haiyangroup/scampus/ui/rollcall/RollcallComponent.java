package com.haiyangroup.scampus.ui.rollcall;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.RollcallModel;
import com.haiyangroup.scampus.present.RollcallPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 点名页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = RollcallModel.class
)
@MyScope
public interface RollcallComponent extends HasPresenter<RollcallPresenter> {
    void inject(RollcallFragment fragment);
    void inject(HomeworkSetFragment fragment);
}
