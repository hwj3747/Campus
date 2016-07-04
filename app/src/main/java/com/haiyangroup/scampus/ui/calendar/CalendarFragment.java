package com.haiyangroup.scampus.ui.calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.CalendarEntity;
import com.haiyangroup.scampus.present.CalendarPresenter;
import com.haiyangroup.scampus.view.CalendarView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 校历页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class CalendarFragment extends BaseFragment<CalendarView, CalendarComponent, CalendarPresenter> implements CalendarView {

    @Inject
    CalendarPresenter presenter;
    @InjectView(R.id.listView)
    ListView listView;

    ArrayList<CalendarEntity> calendarList=new ArrayList<>();
    public CalendarFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_calendar;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.calendar);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {

        presenter.init();
//        CalendarEntity calendarEntity=new CalendarEntity();
//        calendarEntity.setCalendarPic(R.drawable.icon_calendar_kaixue+"");
//        calendarEntity.setCalendarDay("开学【02-19日】");
//
//        CalendarEntity calendarEntity1=new CalendarEntity();
//        calendarEntity1.setCalendarPic(R.drawable.icon_calendar_qingming+"");
//        calendarEntity1.setCalendarDay("清明节【04-01日-04-03日】");
//
//        CalendarEntity calendarEntity2=new CalendarEntity();
//        calendarEntity2.setCalendarPic(R.drawable.icon_calendar_wuyi+"");
//        calendarEntity2.setCalendarDay("五一【05-01日-05-03日】");
//
//        CalendarEntity calendarEntity3=new CalendarEntity();
//        calendarEntity3.setCalendarPic(R.drawable.icon_calendar_duanwu+"");
//        calendarEntity3.setCalendarDay("端午【06-09日-06-11日】");
//
//        CalendarEntity calendarEntity4=new CalendarEntity();
//        calendarEntity4.setCalendarPic(R.drawable.icon_calendar_fangjia + "");
//        calendarEntity4.setCalendarDay("放假【07-04日】");
//
//        calendarList.add(calendarEntity);
//        calendarList.add(calendarEntity1);
//        calendarList.add(calendarEntity2);
//        calendarList.add(calendarEntity3);
//        calendarList.add(calendarEntity4);

//        CalendarAdapter adapter=new CalendarAdapter(getBaseContext(),calendarList);
//        listView.setAdapter(adapter);
    }

    @Override
    protected CalendarComponent onCreateNonConfigurationComponent() {
        return DaggerCalendarComponent.builder()
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
    public void showData(ArrayList<CalendarEntity> entity) {
        calendarList=entity;
        CalendarAdapter adapter=new CalendarAdapter(getBaseContext(),calendarList);
        listView.setAdapter(adapter);
    }
}
