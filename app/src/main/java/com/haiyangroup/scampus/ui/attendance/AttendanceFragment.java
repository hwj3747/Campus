package com.haiyangroup.scampus.ui.attendance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.AttendanceEntity;
import com.haiyangroup.scampus.present.AttendancePresenter;
import com.haiyangroup.scampus.ui.attendanceinfo.AttendanceInfoActivity;
import com.haiyangroup.scampus.view.AttendanceView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 学生考勤页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AttendanceFragment extends BaseFragment<AttendanceView, AttendanceComponent, AttendancePresenter> implements AttendanceView {

    @Inject
    AttendancePresenter presenter;

    ArrayList<AttendanceEntity> attendanceEntities = new ArrayList<>();
    AttendanceAdapter attendanceAdapter;
    String week;
    @InjectView(R.id.listView)
    ListView listView;

    public AttendanceFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_attendance;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.attendance);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {

        week=SharedPreferencesUtil.getInstance(getBaseContext()).getString("week");
        presenter.getAttendance(week);


    }

    @Override
    protected AttendanceComponent onCreateNonConfigurationComponent() {
        return DaggerAttendanceComponent.builder()
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

    public void setWeek(String week) {
        week=week.replace("第","");
        week=week.replace("周","");
        this.week = week;
        presenter.getAttendance(week);
    }

    @Override
    public void showAttendance(ArrayList<AttendanceEntity> entities) {
        attendanceEntities=entities;
        attendanceAdapter=new AttendanceAdapter(getBaseContext(),attendanceEntities);
        listView.setAdapter(attendanceAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AttendanceInfoActivity.launch(getBaseContext(),attendanceEntities.get(position).getLesson(),week,attendanceEntities.get(position).getId());
            }
        });
        attendanceAdapter.notifyDataSetChanged();
    }
}
