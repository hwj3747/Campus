package com.haiyangroup.scampus.ui.feedback;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.FeedbackPresenter;
import com.haiyangroup.scampus.view.FeedbackView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 意见反馈页面的fragment实现
 *
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class FeedbackFragment extends BaseFragment<FeedbackView, FeedbackComponent, FeedbackPresenter> implements FeedbackView {

    @Inject
    FeedbackPresenter presenter;
    @InjectView(R.id.feedback_text)
    EditText feedbackText;

    public FeedbackFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.feedback);
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
    protected FeedbackComponent onCreateNonConfigurationComponent() {
        return DaggerFeedbackComponent.builder()
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

    public void submit() {
        presenter.feedBack(feedbackText.getText().toString());
    }
}
