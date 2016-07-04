package com.haiyangroup.scampus.ui.attendance_teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.AttendanceTeacherPresenter;
import com.haiyangroup.scampus.ui.attendance_teacher_info.AttendanceTeacherInfoActivity;
import com.haiyangroup.scampus.util.CircleImageView;
import com.haiyangroup.scampus.view.AttendanceTeacherView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 *教师出勤fragment
 *
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AttendanceTeacherFragment extends BaseFragment<AttendanceTeacherView, AttendanceTeacherComponent, AttendanceTeacherPresenter> implements AttendanceTeacherView {

    @Inject
    AttendanceTeacherPresenter presenter;
    @InjectView(R.id.head_image)
    CircleImageView headImage;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.sex)
    TextView sex;
    @InjectView(R.id.type)
    TextView type;
    @InjectView(R.id.department)
    TextView department;
    @InjectView(R.id.normal_number)
    TextView normalNumber;
    @InjectView(R.id.normal_course)
    RelativeLayout normalCourse;
    @InjectView(R.id.late_number)
    TextView lateNumber;
    @InjectView(R.id.late_course)
    RelativeLayout lateCourse;
    @InjectView(R.id.registration_number)
    TextView registrationNumber;
    @InjectView(R.id.registration_course)
    RelativeLayout registrationCourse;
    @InjectView(R.id.absent_number)
    TextView absentNumber;
    @InjectView(R.id.absent_course)
    RelativeLayout absentCourse;
    @InjectView(R.id.week_range_layout)
    LinearLayout weekRangeLayout;
    @InjectView(R.id.one_week)
    TextView oneWeek;
    @InjectView(R.id.four_week)
    TextView fourWeek;
    @InjectView(R.id.week_range)
    TextView weekRange;

    public AttendanceTeacherFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_attendance_teacher;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.attendance_teacher);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {

    }

    @Override
    protected AttendanceTeacherComponent onCreateNonConfigurationComponent() {
        return DaggerAttendanceTeacherComponent.builder()
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

    @OnClick({R.id.normal_course, R.id.late_course, R.id.registration_course, R.id.absent_course, R.id.one_week, R.id.four_week, R.id.week_range})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_course:
                AttendanceTeacherInfoActivity.launch(getBaseContext(),"正常");
                break;
            case R.id.late_course:
                AttendanceTeacherInfoActivity.launch(getBaseContext(),"迟到");
                break;
            case R.id.registration_course:
                AttendanceTeacherInfoActivity.launch(getBaseContext(),"补登");
                break;
            case R.id.absent_course:
                AttendanceTeacherInfoActivity.launch(getBaseContext(),"缺勤");
                break;
            case R.id.one_week:
                fourWeek.setBackground(getResources().getDrawable(R.drawable.textview_right_bg));
                oneWeek.setBackground(getResources().getDrawable(R.drawable.textview_left__full_bg));
                weekRangeLayout.setBackground(getResources().getDrawable(R.drawable.button_style_empty));
                break;
            case R.id.four_week:
                fourWeek.setBackground(getResources().getDrawable(R.drawable.textview_right_full_bg));
                oneWeek.setBackground(getResources().getDrawable(R.drawable.textview_left_bg));
                weekRangeLayout.setBackground(getResources().getDrawable(R.drawable.button_style_empty));
                break;
            case R.id.week_range:
                break;
        }
    }
}
