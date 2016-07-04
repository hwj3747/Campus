package com.haiyangroup.scampus.ui.card_consumption;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.CardConsumptionEntity;
import com.haiyangroup.scampus.present.CardConsumptionPresenter;
import com.haiyangroup.scampus.view.CardConsumptionView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 校园卡消费fragment
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class CardConsumptionFragment extends BaseFragment<CardConsumptionView,CardConsumptionComponent,CardConsumptionPresenter> implements CardConsumptionView {

    @Inject
    CardConsumptionPresenter presenter;

    ArrayList<CardConsumptionEntity> cardConsumptionEntities = new ArrayList<>();
    CardConsumptionMonthAdapter cardConsumptionMonthAdapter;
    CardConsumptionYearAdapter cardConsumptionYearAdapter;
    @InjectView(R.id.listView)
    ListView listView;

    public CardConsumptionFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_card_consumption;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.card_consumption);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        CardConsumptionEntity cardConsumptionEntity = new CardConsumptionEntity();
        cardConsumptionEntity.setDate("2016-05-16 11:00");
        cardConsumptionEntity.setBalance_num("858");
        cardConsumptionEntity.setConsumption_num("4");
        cardConsumptionEntity.setExpend_name("教工餐费快餐");

        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);
        cardConsumptionEntities.add(cardConsumptionEntity);

        if(getArguments().getString("type").equals("month")) {
            cardConsumptionMonthAdapter = new CardConsumptionMonthAdapter(getBaseContext(), cardConsumptionEntities);
            listView.setAdapter(cardConsumptionMonthAdapter);
        }
        else{
            cardConsumptionEntity.setConsumption_num("400");
            cardConsumptionEntity.setExpend_name("二餐出纳机");
            cardConsumptionYearAdapter = new CardConsumptionYearAdapter(getBaseContext(), cardConsumptionEntities);
            listView.setAdapter(cardConsumptionYearAdapter);
        }
    }

    @Override
    protected CardConsumptionComponent onCreateNonConfigurationComponent() {
        return DaggerCardConsumptionComponent.builder()
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
