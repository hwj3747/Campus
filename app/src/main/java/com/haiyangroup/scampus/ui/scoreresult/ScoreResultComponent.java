package com.haiyangroup.scampus.ui.scoreresult;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.ScoreResultModel;
import com.haiyangroup.scampus.present.ScoreResultPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 成绩查询结果页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = ScoreResultModel.class
)
@MyScope
public interface ScoreResultComponent extends HasPresenter<ScoreResultPresenter> {
    void inject(ScoreResultFragment fragment);
}
