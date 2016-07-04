package com.haiyangroup.scampus.ui.news;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.NewsModel;
import com.haiyangroup.scampus.present.NewsPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 学校新闻页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = NewsModel.class
)
@MyScope
public interface NewsComponent extends HasPresenter<NewsPresenter> {
    void inject(NewsFragment fragment);
}
