package com.haiyangroup.scampus.ui.card;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.CardModel;
import com.haiyangroup.scampus.present.CardPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 一卡通查询dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = CardModel.class
)
@MyScope
public interface CardComponent extends HasPresenter<CardPresenter> {
    void inject(CardFragment fragment);
}
