package com.haiyangroup.scampus.ui.agenda_add;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.AgendaAddModel;
import com.haiyangroup.scampus.present.AgendaAddPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = AgendaAddModel.class
)
@MyScope
public interface AgendaAddComponent extends HasPresenter<AgendaAddPresenter> {
    void inject(AgendaAddFragment fragment);
}
