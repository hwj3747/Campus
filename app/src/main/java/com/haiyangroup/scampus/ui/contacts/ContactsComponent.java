package com.haiyangroup.scampus.ui.contacts;


import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.ContactsModel;
import com.haiyangroup.scampus.present.ContactsPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 联系人页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = ContactsModel.class
)
@MyScope
public interface ContactsComponent extends HasPresenter<ContactsPresenter> {
    void inject(ContactsFragment fragment);
}
