package com.haiyangroup.scampus.ui.feedback;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.FeedbackModel;
import com.haiyangroup.scampus.present.FeedbackPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 意见反馈页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = FeedbackModel.class
)
@MyScope
public interface FeedbackComponent extends HasPresenter<FeedbackPresenter> {
    void inject(FeedbackFragment fragment);
}
