package com.haiyangroup.scampus.ui.attendance_teacher;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.AttendanceTeacherModel;
import com.haiyangroup.scampus.present.AttendanceTeacherPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = AttendanceTeacherModel.class
)
@MyScope
public interface AttendanceTeacherComponent extends HasPresenter<AttendanceTeacherPresenter> {
    void inject(AttendanceTeacherFragment fragment);
}
