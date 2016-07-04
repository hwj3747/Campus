package com.haiyangroup.scampus.ui.rollcall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.RollcallPresenter;
import com.haiyangroup.scampus.view.RollcallView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 点名页面的切换布置作业fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class HomeworkSetFragment extends BaseFragment<RollcallView,RollcallComponent,RollcallPresenter> implements RollcallView {

    @Inject
    RollcallPresenter presenter;

    @InjectView(R.id.feedback_text)
    EditText feedback;

    public HomeworkSetFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_homework_set;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.homework_set);
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
    protected RollcallComponent onCreateNonConfigurationComponent() {
        return DaggerRollcallComponent.builder()
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

    public void saveHomework(String id){
        presenter.saveHomework(id,feedback.getText().toString());
    }
}
