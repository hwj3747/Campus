package com.haiyangroup.scampus.ui.score;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.ConditionEntity;
import com.haiyangroup.scampus.entity.ConditionExamEntity;
import com.haiyangroup.scampus.entity.ConditionResultEntity;
import com.haiyangroup.scampus.present.ScorePresenter;
import com.haiyangroup.scampus.ui.scoreresult.ScoreResultActivity;
import com.haiyangroup.scampus.util.dialog.FourNumberPickerDialog;
import com.haiyangroup.scampus.util.dialog.OneNumberPickerDialog;
import com.haiyangroup.scampus.view.ScoreView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 成绩查询页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ScoreFragment extends BaseFragment<ScoreView, ScoreComponent, ScorePresenter> implements ScoreView {

    @Inject
    ScorePresenter presenter;

    @InjectView(R.id.course)
    RelativeLayout course;
    @InjectView(R.id.examination)
    RelativeLayout examination;
    @InjectView(R.id.className)
    RelativeLayout className;
    @InjectView(R.id.query)
    Button query;
    @InjectView(R.id.tv_course)
    TextView tvCourse;
    @InjectView(R.id.tv_examination)
    TextView tvExamination;
    @InjectView(R.id.tv_classname)
    TextView tvClassname;

    @State ConditionResultEntity conditionResultEntity;

    ArrayList<String> subjectList=new ArrayList<>();
    ArrayList<String> yearList=new ArrayList<>();
    ArrayList<String> termList=new ArrayList<>();
    ArrayList<String> typeList=new ArrayList<>();
    ArrayList<String> classList=new ArrayList<>();

    public ScoreFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_score;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.score);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        presenter.getCondition();
    }

    @Override
    protected ScoreComponent onCreateNonConfigurationComponent() {
        return DaggerScoreComponent.builder()
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

    @OnClick({R.id.course, R.id.examination, R.id.className, R.id.query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.course:
                String[] array={"大学英语","高等数学","计算机基础","大学语文"};
                new OneNumberPickerDialog(getBaseContext(),subjectList.toArray(new String[subjectList.size()]),(text ->tvCourse.setText(text))).showDialog();
                break;
            case R.id.examination:
                String[] array3={"第一学期","第二学期"};
                String[] array4={"期中考","期末考"};
                new FourNumberPickerDialog(getBaseContext(),yearList.toArray(new String[yearList.size()]),
                        termList.toArray(new String[termList.size()]),
                        typeList.toArray(new String[typeList.size()]),(text ->tvExamination.setText(text))).showDialog();
                break;
            case R.id.className:
                String[] array1={"软件工程1班","软件工程2班","软件工程3班"};
                new OneNumberPickerDialog(getBaseContext(),classList.toArray(new String[classList.size()]),(text ->tvClassname.setText(text))).showDialog();
                break;
            case R.id.query:
                String temp[]=tvExamination.getText().toString().split(" ");
                ScoreResultActivity.launch(getBaseContext(),
                        findConditionId(tvCourse.getText().toString()),
                        findExamId(temp[0], "year"),
                        findExamId(temp[1], "term"),
                        findExamId(temp[2], "type"),
                        findConditionId(tvClassname.getText().toString()));
                break;
        }
    }

    @Override
    public void showCondition(ConditionResultEntity entity) {
        conditionResultEntity=entity;
        tvCourse.setText(entity.getSubject().get(0).getName());
        tvClassname.setText(entity.getClassName().get(0).getName());
        tvExamination.setText(entity.getExam().getYear().get(0).getLabel()+" "+entity.getExam().getTerm().get(0).getLabel()+" "+entity.getExam().getType().get(0).getLabel());
        for(ConditionEntity conditionEntity:entity.getSubject()){
            subjectList.add(conditionEntity.getName());
        }
        for(ConditionEntity conditionEntity:entity.getClassName()){
            classList.add(conditionEntity.getName());
        }
        for(ConditionExamEntity conditionExamEntity:entity.getExam().getYear()){
            yearList.add(conditionExamEntity.getLabel());
        }

        for(ConditionExamEntity conditionExamEntity:entity.getExam().getTerm()){
            termList.add(conditionExamEntity.getLabel());
        }

        for(ConditionExamEntity conditionExamEntity:entity.getExam().getType()){
            typeList.add(conditionExamEntity.getLabel());
        }
    }

    private String findConditionId(String name){
        for(ConditionEntity conditionEntity:conditionResultEntity.getSubject()){
            if(conditionEntity.getName().equals(name))
                return conditionEntity.getId();
        }
        return "";
    }

    private String findExamId(String name,String type){
        if(type.equals("year")) {
            for (ConditionExamEntity conditionExamEntity : conditionResultEntity.getExam().getYear()) {
                if(conditionExamEntity.getLabel().equals(name))
                    return conditionExamEntity.getValue();
            }
        }
        if(type.equals("term")) {
            for (ConditionExamEntity conditionExamEntity : conditionResultEntity.getExam().getTerm()) {
                if(conditionExamEntity.getLabel().equals(name))
                    return conditionExamEntity.getValue();
            }
        }
        if(type.equals("type")) {
            for (ConditionExamEntity conditionExamEntity : conditionResultEntity.getExam().getType()) {
                if(conditionExamEntity.getLabel().equals(name))
                    return conditionExamEntity.getValue();
            }
        }
        return "";
    }
}
