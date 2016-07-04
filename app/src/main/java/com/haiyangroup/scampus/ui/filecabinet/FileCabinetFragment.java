package com.haiyangroup.scampus.ui.filecabinet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseActivity;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.FolderEntity;
import com.haiyangroup.scampus.present.FileCabinetPresenter;
import com.haiyangroup.scampus.view.FileCabinetView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 个人文件柜fragment
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class FileCabinetFragment extends BaseFragment<FileCabinetView,FileCabinetComponent,FileCabinetPresenter> implements FileCabinetView {

    @Inject
    FileCabinetPresenter presenter;

    ArrayList<FolderEntity> folderEntities = new ArrayList<>();
    FileCabinetAdapter fileCabinetAdapter;

    @InjectView(R.id.listView)
    ListView listView;

    public FileCabinetFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_file_cabinet;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.file_cabinet);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        Log.i("count", "fragment");
        FolderEntity folderEntity=new FolderEntity();
        folderEntity.setName("入党思想汇报");
        folderEntities.add(folderEntity);
        folderEntities.add(folderEntity);
        folderEntities.add(folderEntity);
        folderEntities.add(folderEntity);
        folderEntities.add(folderEntity);
        folderEntities.add(folderEntity);
        folderEntities.add(folderEntity);

        fileCabinetAdapter=new FileCabinetAdapter(getBaseContext(),folderEntities);
        listView.setAdapter(fileCabinetAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((BaseActivity)getBaseContext()).showContent(new FileCabinetNextFragment(),R.id.file_cabinet);
            }
        });

    }

    @Override
    protected FileCabinetComponent onCreateNonConfigurationComponent() {
        return DaggerFileCabinetComponent.builder()
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
