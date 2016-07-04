package com.haiyangroup.scampus.ui.approval;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.common.CommonConfig;
import com.haiyangroup.scampus.entity.NoteEntity;
import com.haiyangroup.scampus.present.ApprovalPresenter;
import com.haiyangroup.scampus.util.TimeUtil;
import com.haiyangroup.scampus.util.dialog.CheckDialog;
import com.haiyangroup.scampus.util.dialog.PickListen;
import com.haiyangroup.scampus.util.rximageloader.RxImageLoader;
import com.haiyangroup.scampus.view.ApprovalView;
import com.haiyangroup.library.view.list.XListView;
import com.haiyangroup.library.view.list.adapter.ListViewDataAdapter;
import com.haiyangroup.library.view.list.adapter.ViewHolderBase;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

import static com.haiyangroup.scampus.common.App.getAppComponent;

/**
 * 请假审批页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ApprovalFragment extends BaseFragment<ApprovalView, ApprovalComponent, ApprovalPresenter> implements ApprovalView, XListView.IXListViewListener {

    @Inject
    ApprovalPresenter presenter;
    @InjectView(R.id.tv_left)
    TextView tvLeft;
    @InjectView(R.id.tv_right)
    TextView tvRight;
    @InjectView(R.id.left_line)
    TextView leftLine;
    @InjectView(R.id.right_line)
    TextView rightLine;
    @InjectView(R.id.xlistview)
    XListView xlistview;

    Boolean isComplete = false;
    private ListViewDataAdapter<NoteEntity> mListViewAdapter;
    Boolean flag=false;
    int index = 1;

    int i;
    @State
    ArrayList<NoteEntity> NoteEntities = new ArrayList<>();

    public ApprovalFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_approval;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.approval);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        init();
        presenter.getLeaveFalse(index);
    }

    @Override
    protected ApprovalComponent onCreateNonConfigurationComponent() {
        return DaggerApprovalComponent.builder()
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

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                index=1;
                flag=false;
                isComplete = false;
                NoteEntities.clear();
                showUncheck();
                presenter.getLeaveFalse(index);
                break;
            case R.id.tv_right:
                index=1;
                flag=true;
                isComplete = false;
                NoteEntities.clear();
                showCheck();
                presenter.getLeaveTrue(index);
                break;
        }
    }


    public void init() {
        xlistview.setXListViewListener(this);
        if (mListViewAdapter == null) {
            mListViewAdapter = new ListViewDataAdapter<NoteEntity>(position -> {
                return new ViewHolderBase<NoteEntity>() {
                    ImageView head_imag;
                    TextView name, check, day, reason, time;
                    LinearLayout pass, nopass;

                    //   public RelativeLayout mLl_message;
                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View view;
                        view = layoutInflater.inflate(R.layout.item_approval, null);
                        head_imag = (ImageView) view.findViewById(R.id.head_image);
                        name = (TextView) view.findViewById(R.id.name);
                        check = (TextView) view.findViewById(R.id.check);
                        day = (TextView) view.findViewById(R.id.day);
                        reason = (TextView) view.findViewById(R.id.reason);
                        time = (TextView) view.findViewById(R.id.time);
                        pass = (LinearLayout) view.findViewById(R.id.pass);
                        nopass = (LinearLayout) view.findViewById(R.id.nopass);
                        return view;
                    }

                    @Override
                    public void showData(int position, final NoteEntity itemData) {
                        head_imag.setImageResource(R.drawable.head_image);

                        head_imag.setTag(null);
                        head_imag.setTag(itemData.getPhoto());
                        if(itemData.getPhoto()!=null&&!itemData.getPhoto().equals(""))
                            RxImageLoader.getLoaderObservable(head_imag,itemData.getPhoto(),"").subscribe();
                        Long temp=itemData.getEnd()-itemData.getStart();
                        String daytime=temp/(1000*60*60*24)+"";
                        day.setText(TimeUtil.timestamp2str(new Timestamp(itemData.getStart()),"MM/dd")+"-"+
                                        TimeUtil.timestamp2str(new Timestamp(itemData.getEnd()),"MM/dd")+" 共"+daytime+"天"
                        );
                        reason.setText("【理由："+itemData.getReason()+"】");
                        time.setText(XListView.topTimeFormat(TimeUtil.timestamp2str(new Timestamp(itemData.getDate()), "")));
                        name.setText(itemData.getName());

                        check.setVisibility(View.GONE);
                        pass.setVisibility(View.GONE);
                        nopass.setVisibility(View.GONE);
                        if (itemData.getDealId()==null||itemData.getDealId().equals("")) {
                            check.setVisibility(View.VISIBLE);
                            check.setOnClickListener(v -> {
                                new CheckDialog(getBaseContext(),itemData.getPhoto(),
                                        itemData.getName(), TimeUtil.timestamp2str(new Timestamp(itemData.getStart()),"MM/dd")+"-"+
                                        TimeUtil.timestamp2str(new Timestamp(itemData.getEnd()),"MM/dd")+" 共"+daytime+"天",
                                        "【理由："+itemData.getReason()+"】", new PickListen() {
                                    @Override
                                    public void show(String text) {
                                        i=position;
                                        presenter.LeaveApproval(itemData.getId(),text.equals("true")?"1":"0");
                                    }
                                }).show();
                            });
                        } else if (itemData.getDealId().equals("1")) {
                            pass.setVisibility(View.VISIBLE);
                        } else if (itemData.getDealId().equals("0")) {
                            nopass.setVisibility(View.VISIBLE);
                        }

                    }
                };
            }
            );
        }
        xlistview.setAdapter(mListViewAdapter);
    }

    @Override
    public void onRefresh() {
        index=1;
        NoteEntities=new ArrayList<>();
        isComplete = false;
        if(flag)
            presenter.getLeaveTrue(index);
        else
            presenter.getLeaveFalse(index);
    }

    public void Refresh() {
        if (mListViewAdapter == null) {
            init();
        }
        mListViewAdapter.getDataList().clear();
        mListViewAdapter.getDataList().addAll(NoteEntities);
        mListViewAdapter.notifyDataSetChanged();
        xlistview.onLoadComplete(true);
    }

    @Override
    public void onLoadMore() {
        if(flag)
            presenter.getLeaveTrue(index);
        else
            presenter.getLeaveFalse(index);
    }

    @Override
    public void onStartPullDown() {

    }

    public void showCheck(){
        tvRight.setTextColor(getResources().getColor(R.color.color_primary_green));
        tvLeft.setTextColor(getResources().getColor(R.color.text_color));
        rightLine.setBackgroundColor(getResources().getColor(R.color.color_primary_green));
        leftLine.setBackgroundColor(getResources().getColor(R.color.separator));
    }

    public void showUncheck(){
        tvLeft.setTextColor(getResources().getColor(R.color.color_primary_green));
        tvRight.setTextColor(getResources().getColor(R.color.text_color));
        leftLine.setBackgroundColor(getResources().getColor(R.color.color_primary_green));
        rightLine.setBackgroundColor(getResources().getColor(R.color.separator));
    }

    @Override
    public void showNote(ArrayList<NoteEntity> entities) {
        NoteEntities.addAll(entities);
        index++;
        if(entities.size()< CommonConfig.pageSize){
            isComplete=true;
        }
        Refresh();
    }

    @Override
    public void refresh() {
        NoteEntities.remove(i);
        Refresh();
    }
}
