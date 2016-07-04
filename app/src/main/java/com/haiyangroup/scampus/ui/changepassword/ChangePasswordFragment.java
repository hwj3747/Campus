package com.haiyangroup.scampus.ui.changepassword;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.present.ChangePasswordPresenter;
import com.haiyangroup.scampus.view.ChangePasswordView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 更改密码页面的fragment实现
 *
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ChangePasswordFragment extends BaseFragment<ChangePasswordView, ChangePasswordComponent, ChangePasswordPresenter> implements ChangePasswordView {

    @Inject
    ChangePasswordPresenter presenter;
    @InjectView(R.id.old_password)
    EditText oldPassword;
    @InjectView(R.id.new_password)
    EditText newPassword;
    @InjectView(R.id.new_password_confirm)
    EditText newPasswordConfirm;
    @InjectView(R.id.change)
    Button change;

    public ChangePasswordFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_change_password;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.change_password);
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
    protected ChangePasswordComponent onCreateNonConfigurationComponent() {
        return DaggerChangePasswordComponent.builder()
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

    @OnClick(R.id.change) public void submit(){
        if(oldPassword.getText().toString().equals("")||newPassword.getText().toString().equals("")||newPasswordConfirm.getText().toString().equals(""))
            Toast.makeText(getBaseContext(),"密码不能为空", Toast.LENGTH_SHORT).show();
        else if(!newPassword.getText().toString().equals(newPasswordConfirm.getText().toString()))
            Toast.makeText(getBaseContext(),"两次输入不一致", Toast.LENGTH_SHORT).show();
        else
            presenter.modifyPassword(oldPassword.getText().toString(),newPassword.getText().toString());
    }
}
