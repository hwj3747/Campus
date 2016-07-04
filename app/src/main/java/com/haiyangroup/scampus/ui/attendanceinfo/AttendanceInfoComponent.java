package com.haiyangroup.scampus.ui.attendanceinfo;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.AttendanceInfoModel;
import com.haiyangroup.scampus.present.AttendanceInfoPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 学生考勤详情页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = AttendanceInfoModel.class
)
@MyScope
public interface AttendanceInfoComponent extends HasPresenter<AttendanceInfoPresenter> {
    void inject(AttendanceInfoFragment fragment);
}
