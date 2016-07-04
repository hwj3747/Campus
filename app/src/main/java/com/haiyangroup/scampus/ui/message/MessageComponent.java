package com.haiyangroup.scampus.ui.message;


import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.MessageModel;
import com.haiyangroup.scampus.present.MessagePresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 消息页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = MessageModel.class
)
@MyScope
public interface MessageComponent extends HasPresenter<MessagePresenter> {
    void inject(MessageFragment fragment);
}
