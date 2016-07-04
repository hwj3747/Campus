package com.haiyangroup.scampus.ui.scoreresult;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.ScoreEntity;
import com.haiyangroup.scampus.present.ScoreResultPresenter;
import com.haiyangroup.scampus.view.ScoreResultView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 成绩查询结果页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ScoreResultFragment extends BaseFragment<ScoreResultView, ScoreResultComponent, ScoreResultPresenter> implements ScoreResultView {

    @Inject
    ScoreResultPresenter presenter;
    @InjectView(R.id.listView)
    ListView listView;

    ArrayList<ScoreEntity> scoreList=new ArrayList<>();
    public ScoreResultFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_score_result;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.score_result);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        presenter.getScore(getArguments().getString("subject"), getArguments().getString("year"),getArguments().getString("term"),getArguments().getString("type"), getArguments().getString("className"));

    }

    @Override
    protected ScoreResultComponent onCreateNonConfigurationComponent() {
        return DaggerScoreResultComponent.builder()
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
    public void showScoreResult(ArrayList<ScoreEntity> entities) {
        scoreList=entities;
        ScoreAdapter adapter=new ScoreAdapter(getBaseContext(),scoreList);
        listView.setAdapter(adapter);
    }
}
