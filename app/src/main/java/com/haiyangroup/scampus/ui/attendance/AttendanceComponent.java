package com.haiyangroup.scampus.ui.attendance;



import com.haiyangroup.scampus.common.AppComponent;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.model.AttendanceModel;
import com.haiyangroup.scampus.present.AttendancePresenter;
import com.haiyangroup.scampus.present.NewsPresenter;

import compartment.HasPresenter;
import dagger.Component;

/**
 * 学生考勤页面的dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        dependencies = AppComponent.class, modules = AttendanceModel.class
)
@MyScope
public interface AttendanceComponent extends HasPresenter<AttendancePresenter> {
    void inject(AttendanceFragment fragment);

    //NewsPresenter presenter();
}
