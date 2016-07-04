package com.haiyangroup.scampus.ui.attendance_teacher_info;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.AttendanceTeacherInfoModel;
import com.haiyangroup.scampus.present.AttendanceTeacherInfoPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * dagger配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = AttendanceTeacherInfoModel.class
)
@MyScope
public interface AttendanceTeacherInfoComponent extends HasPresenter<AttendanceTeacherInfoPresenter> {
    void inject(AttendanceTeacherInfoFragment fragment);
}
