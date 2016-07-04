package com.haiyangroup.scampus.ui.about;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.AboutPresenter;
import com.haiyangroup.scampus.view.AboutView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 关于我们页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AboutFragment extends BaseFragment<AboutView,AboutComponent,AboutPresenter> implements AboutView {

    @Inject
    AboutPresenter presenter;//dagger2获取present的实例

    public AboutFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);//icepick恢复状态
    }

    /**
     * 设置此fragment的对应的XML文件
     * 继承自基础fragment，默认调用
     */
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_about;
    }

    /**
     * 设置loading框要显示在哪个view上
     * 继承自基础fragment，默认调用
     */
    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.about);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);//butterknife用注解的方式替代findviewbyid
    }

    /**
     * 初始化fragment
     * 继承自基础fragment，默认调用
     */
    @Override
    protected void onViewInit() {

    }

    @Override
    protected AboutComponent onCreateNonConfigurationComponent() {
        return DaggerAboutComponent.builder()
                .appComponent(getAppComponent(mActivity))
                .build();
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save state of all @State annotated members
        Icepick.saveInstanceState(this, outState);//icepick保存状态
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);//butterknife用注解的方式替代findviewbyid
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
