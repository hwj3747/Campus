package com.haiyangroup.scampus.ui.agenda_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.AgendaContentEntitty;
import com.haiyangroup.scampus.entity.AgendaEntity;
import com.haiyangroup.scampus.entity.CardConsumptionEntity;
import com.haiyangroup.scampus.present.AgendaListPresenter;
import com.haiyangroup.scampus.ui.agenda_add.AgendaAddActivity;
import com.haiyangroup.scampus.ui.agenda_list.DaggerAgendaListComponent;
import com.haiyangroup.scampus.ui.card_consumption.CardConsumptionMonthAdapter;
import com.haiyangroup.scampus.view.AgendaListView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 日程列表fragment
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AgendaListFragment extends BaseFragment<AgendaListView,AgendaListComponent,AgendaListPresenter> implements AgendaListView {

    @Inject
    AgendaListPresenter presenter;
    ArrayList<AgendaEntity> agendaEntities = new ArrayList<>();
    AgendaListAdapter agendaListAdapter;

    @InjectView(R.id.listView)
    ListView listView;

    public AgendaListFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_agenda_list;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.agenda_list);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        AgendaEntity agendaEntity=new AgendaEntity();
        agendaEntity.setDate("2016-5-20");
        agendaEntity.setAgendaName("参加校总部会议");

        agendaEntities.add(agendaEntity);
        agendaEntities.add(agendaEntity);
        agendaEntities.add(agendaEntity);
        agendaEntities.add(agendaEntity);
        agendaEntities.add(agendaEntity);
        agendaEntities.add(agendaEntity);
        agendaEntities.add(agendaEntity);
        agendaEntities.add(agendaEntity);


        agendaListAdapter=new AgendaListAdapter(getBaseContext(),agendaEntities,false);
        listView.setAdapter(agendaListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AgendaContentEntitty agendaContentEntitty=new AgendaContentEntitty();
                agendaContentEntitty.setContent("有一个会议");
                agendaContentEntitty.setStartTime("2016-5-20 08:00");
                agendaContentEntitty.setEndTime("2016-5-20 10:00");
                agendaContentEntitty.setRemind("不提醒");
                agendaContentEntitty.setRepeat("重复");
                AgendaAddActivity.launch(getBaseContext(),agendaContentEntitty);
            }
        });

    }

    @Override
    protected AgendaListComponent onCreateNonConfigurationComponent() {
        return DaggerAgendaListComponent.builder()
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

    public void showDelete(){
        agendaListAdapter.setFlag(true);
        agendaListAdapter.notifyDataSetChanged();
    }

    public void hideDelete(){
        agendaListAdapter.setFlag(false);
        agendaListAdapter.notifyDataSetChanged();
    }
}
