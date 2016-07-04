package com.haiyangroup.scampus.ui.card_info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.CardInfoPresenter;
import com.haiyangroup.scampus.view.CardInfoView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 校园卡详情fragment
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class CardInfoFragment extends BaseFragment<CardInfoView,CardInfoComponent,CardInfoPresenter> implements CardInfoView {

    @Inject
    CardInfoPresenter presenter;

    public CardInfoFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_card_info;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.card_info);
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
    protected CardInfoComponent onCreateNonConfigurationComponent() {
        return DaggerCardInfoComponent.builder()
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
