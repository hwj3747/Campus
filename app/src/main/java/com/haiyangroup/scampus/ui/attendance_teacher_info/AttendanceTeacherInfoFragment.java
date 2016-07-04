package com.haiyangroup.scampus.ui.attendance_teacher_info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.AttendanceTeacherInfoEntity;
import com.haiyangroup.scampus.present.AttendanceTeacherInfoPresenter;
import com.haiyangroup.scampus.view.AttendanceTeacherInfoView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 教师出勤详情fragment
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AttendanceTeacherInfoFragment extends BaseFragment<AttendanceTeacherInfoView,AttendanceTeacherInfoComponent,AttendanceTeacherInfoPresenter> implements AttendanceTeacherInfoView {

    @Inject
    AttendanceTeacherInfoPresenter presenter;

    ArrayList<AttendanceTeacherInfoEntity> attendanceTeacherInfoEntities = new ArrayList<>();
    AttendanceTeacherInfoAdapter attendanceTeacherInfoAdapter;
    @InjectView(R.id.listView)
    ListView listView;

    public AttendanceTeacherInfoFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_attendance_teacher_info;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.attendance_teacher_info);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        AttendanceTeacherInfoEntity attendanceTeacherInfoEntity = new AttendanceTeacherInfoEntity();
        attendanceTeacherInfoEntity.setClassName("数控1401 教室");
        attendanceTeacherInfoEntity.setSection("PRO/E 05-13 3-4节");

        attendanceTeacherInfoEntities.add(attendanceTeacherInfoEntity);
        attendanceTeacherInfoEntities.add(attendanceTeacherInfoEntity);
        attendanceTeacherInfoEntities.add(attendanceTeacherInfoEntity);
        attendanceTeacherInfoEntities.add(attendanceTeacherInfoEntity);
        attendanceTeacherInfoEntities.add(attendanceTeacherInfoEntity);
        attendanceTeacherInfoEntities.add(attendanceTeacherInfoEntity);
        attendanceTeacherInfoEntities.add(attendanceTeacherInfoEntity);
        attendanceTeacherInfoEntities.add(attendanceTeacherInfoEntity);

        attendanceTeacherInfoAdapter=new AttendanceTeacherInfoAdapter(getBaseContext(),attendanceTeacherInfoEntities);
        listView.setAdapter(attendanceTeacherInfoAdapter);
    }

    @Override
    protected AttendanceTeacherInfoComponent onCreateNonConfigurationComponent() {
        return DaggerAttendanceTeacherInfoComponent.builder()
                .appComponent(getAppComponent(mActivity))
                .build();
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save state of all @State annotated members
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
