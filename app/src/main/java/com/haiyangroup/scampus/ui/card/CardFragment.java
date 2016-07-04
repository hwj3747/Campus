package com.haiyangroup.scampus.ui.card;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.CardPresenter;
import com.haiyangroup.scampus.ui.card_consumption.CardConsumptionActivity;
import com.haiyangroup.scampus.ui.card_info.CardInfoActivity;
import com.haiyangroup.scampus.view.CardView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 一卡通查询fragment
 *
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class CardFragment extends BaseFragment<CardView, CardComponent, CardPresenter> implements CardView {

    @Inject
    CardPresenter presenter;
    @InjectView(R.id.card_info)
    RelativeLayout cardInfo;
    @InjectView(R.id.month)
    RelativeLayout month;
    @InjectView(R.id.year)
    RelativeLayout year;
    @InjectView(R.id.balance)
    TextView balance;
    @InjectView(R.id.purchase_history)
    TextView purchaseHistory;
    @InjectView(R.id.recharge_record)
    TextView rechargeRecord;

    public CardFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_card;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.card);
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
    protected CardComponent onCreateNonConfigurationComponent() {
        return DaggerCardComponent.builder()
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

    @OnClick({R.id.card_info, R.id.month, R.id.year})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_info:
                CardInfoActivity.launch(getBaseContext());
                break;
            case R.id.month:
                CardConsumptionActivity.launch(getBaseContext(),"month");
                break;
            case R.id.year:
                CardConsumptionActivity.launch(getBaseContext(),"year");
                break;
        }
    }
}
