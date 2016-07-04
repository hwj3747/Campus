package com.haiyangroup.scampus.ui.agenda_add;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.AgendaContentEntitty;
import com.haiyangroup.scampus.present.AgendaAddPresenter;
import com.haiyangroup.scampus.util.TimeUtil;
import com.haiyangroup.scampus.util.dialog.OneNumberPickerDialog;
import com.haiyangroup.scampus.util.dialog.PickListen;
import com.haiyangroup.scampus.util.dialog.ThreeNumberPickerDialog;
import com.haiyangroup.scampus.view.AgendaAddView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 添加日程fragment
 *
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AgendaAddFragment extends BaseFragment<AgendaAddView, AgendaAddComponent, AgendaAddPresenter> implements AgendaAddView {

    @Inject
    AgendaAddPresenter presenter;
    @InjectView(R.id.content)
    EditText content;
    @InjectView(R.id.start_time)
    TextView startTime;
    @InjectView(R.id.over_time)
    TextView overTime;
    @InjectView(R.id.remind)
    TextView remind;
    @InjectView(R.id.repeat)
    TextView repeat;
    @InjectView(R.id.commit)
    Button commit;

    public AgendaAddFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_agenda_add;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.agenda_add);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {

        if(getArguments()!=null){
            AgendaContentEntitty agendaContentEntitty=(AgendaContentEntitty)getArguments().get("agenda");
            content.setText(agendaContentEntitty.getContent());
            startTime.setText(agendaContentEntitty.getStartTime());
            overTime.setText(agendaContentEntitty.getEndTime());
            remind.setText(agendaContentEntitty.getRemind());
            repeat.setText(agendaContentEntitty.getRepeat());
        }
        else{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); //设置时间格式
            Calendar cal = Calendar.getInstance();
            startTime.setText(sdf.format(cal.getTime()));
            cal.add(Calendar.MINUTE, 1);
            overTime.setText(sdf.format(cal.getTime()));
        }
    }

    @Override
    protected AgendaAddComponent onCreateNonConfigurationComponent() {
        return DaggerAgendaAddComponent.builder()
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

    @OnClick({R.id.start_time, R.id.over_time, R.id.remind_layout, R.id.repeat_layout, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_time:
                Log.i("size",getResources().getStringArray(R.array.minute).length+"");
                new ThreeNumberPickerDialog(getBaseContext(),365,getResources().getStringArray(R.array.hour),getResources().getStringArray(R.array.minute),
                        (text)->{
                                startTime.setText(text);
                        }).showDialog();
                break;
            case R.id.over_time:
                new ThreeNumberPickerDialog(getBaseContext(),365,getResources().getStringArray(R.array.hour),getResources().getStringArray(R.array.minute),
                        (text)->{
                                overTime.setText(text);
                        }).showDialog();
                break;
            case R.id.remind_layout:
                String listShow[]={"当天提醒","不提醒","提早两天提醒"};
                new OneNumberPickerDialog(getBaseContext(), listShow, (text)->{remind.setText(text);}).showDialog();
                break;
            case R.id.repeat_layout:
                String listShow2[]={"每日重复","不重复","每周重复"};
                new OneNumberPickerDialog(getBaseContext(), listShow2, (text)->{repeat.setText(text);}).showDialog();
                break;
            case R.id.commit:
                if(TimeUtil.compareTime(startTime.getText().toString(),overTime.getText().toString())) {
                    Toast.makeText(getBaseContext(),"提交成功！",Toast.LENGTH_SHORT).show();
                    AgendaAddActivity.instance.finish();
                }
                else{
                    Toast.makeText(getBaseContext(),"结束时间必须大于开始时间！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
