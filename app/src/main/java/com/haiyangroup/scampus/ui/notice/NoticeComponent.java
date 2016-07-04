package com.haiyangroup.scampus.ui.notice;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.NoticeModel;
import com.haiyangroup.scampus.present.NoticePresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 公告通知页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = NoticeModel.class
)
@MyScope
public interface NoticeComponent extends HasPresenter<NoticePresenter> {
    void inject(NoticeFragment fragment);
}
