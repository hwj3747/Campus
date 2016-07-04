package com.haiyangroup.scampus.ui.owner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.OwnerPresenter;
import com.haiyangroup.scampus.util.CircleImageView;
import com.haiyangroup.scampus.view.OwnerView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 *个人中心页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class OwnerFragment extends BaseFragment<OwnerView, OwnerComponent, OwnerPresenter> implements OwnerView {

    @Inject
    OwnerPresenter presenter;
    @InjectView(R.id.head_image)
    CircleImageView headImage;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.sex)
    TextView sex;
    @InjectView(R.id.campus_name)
    TextView campusName;
    @InjectView(R.id.phone)
    TextView phone;
    @InjectView(R.id.mail)
    TextView mail;
    @InjectView(R.id.department)
    TextView department;
    @InjectView(R.id.owner_class)
    TextView ownerClass;
    @InjectView(R.id.course)
    TextView course;

    public OwnerFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_owner;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.owner);
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
    protected OwnerComponent onCreateNonConfigurationComponent() {
        return DaggerOwnerComponent.builder()
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
