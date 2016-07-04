package com.haiyangroup.scampus.ui.card_info;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.CardInfoModel;
import com.haiyangroup.scampus.present.CardInfoPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = CardInfoModel.class
)
@MyScope
public interface CardInfoComponent extends HasPresenter<CardInfoPresenter> {
    void inject(CardInfoFragment fragment);
}
