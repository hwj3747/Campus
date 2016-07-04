package com.haiyangroup.scampus.ui.attendanceinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.AttendanceInfoEntity;
import com.haiyangroup.scampus.present.AttendanceInfoPresenter;
import com.haiyangroup.scampus.view.AttendanceInfoView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 学生考勤详情页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AttendanceInfoFragment extends BaseFragment<AttendanceInfoView,AttendanceInfoComponent,AttendanceInfoPresenter> implements AttendanceInfoView {

    @Inject
    AttendanceInfoPresenter presenter;

    @InjectView(R.id.listView)
    ListView listView;

    ArrayList<AttendanceInfoEntity> attendanceInfoEntities=new ArrayList<>();

    AttendanceInfoAdapter attendanceInfoAdapter;
    public AttendanceInfoFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_attendance_info;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.attendance_info);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        String id=getArguments().getString("id");
        String week=getArguments().getString("week");

        presenter.getAttendanceInfo(week, id);
    }

    @Override
    protected AttendanceInfoComponent onCreateNonConfigurationComponent() {
        return DaggerAttendanceInfoComponent.builder()
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

    @Override
    public void showAttendanceInfo(ArrayList<AttendanceInfoEntity> entities) {
        attendanceInfoEntities=entities;
        attendanceInfoAdapter=new AttendanceInfoAdapter(getBaseContext(),attendanceInfoEntities);
        listView.setAdapter(attendanceInfoAdapter);
    }
}
