package com.haiyangroup.scampus.ui.score;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.ScoreModel;
import com.haiyangroup.scampus.present.ScorePresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 成绩查询页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = ScoreModel.class
)
@MyScope
public interface ScoreComponent extends HasPresenter<ScorePresenter> {
    void inject(ScoreFragment fragment);
}
