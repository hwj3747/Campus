package com.haiyangroup.scampus.ui.test;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.TestModel;
import com.haiyangroup.scampus.present.TestPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 测试dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = TestModel.class
)
@MyScope
public interface TestComponent extends HasPresenter<TestPresenter> {
    void inject(TestFragment fragment);
}
