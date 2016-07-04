package com.haiyangroup.scampus.ui.filecabinet;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.FileCabinetModel;
import com.haiyangroup.scampus.present.FileCabinetPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 个人文件柜dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = FileCabinetModel.class
)
@MyScope
public interface FileCabinetComponent extends HasPresenter<FileCabinetPresenter> {
    void inject(FileCabinetFragment fragment);
    void inject(FileCabinetNextFragment fragment);
}
