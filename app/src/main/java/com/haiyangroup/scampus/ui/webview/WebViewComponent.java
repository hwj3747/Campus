package com.haiyangroup.scampus.ui.webview;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.present.TestPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 网页浏览页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class
)
@MyScope
public interface WebViewComponent extends HasPresenter<TestPresenter> {
    void inject(WebViewFragment fragment);
}
