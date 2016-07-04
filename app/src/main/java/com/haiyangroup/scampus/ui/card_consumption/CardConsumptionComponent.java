package com.haiyangroup.scampus.ui.card_consumption;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.CardConsumptionModel;
import com.haiyangroup.scampus.present.CardConsumptionPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = CardConsumptionModel.class
)
@MyScope
public interface CardConsumptionComponent extends HasPresenter<CardConsumptionPresenter> {
    void inject(CardConsumptionFragment fragment);
}
