package com.haiyangroup.scampus.ui.agenda_list;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.AgendaListModel;
import com.haiyangroup.scampus.present.AgendaListPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = AgendaListModel.class
)
@MyScope
public interface AgendaListComponent extends HasPresenter<AgendaListPresenter> {
    void inject(AgendaListFragment fragment);
}
