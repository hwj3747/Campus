package com.haiyangroup.scampus.ui.notice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.common.CommonConfig;
import com.haiyangroup.scampus.entity.NewsEntity;
import com.haiyangroup.scampus.entity.NoticeEntity;
import com.haiyangroup.scampus.present.NoticePresenter;
import com.haiyangroup.scampus.ui.webview.WebViewActivity;
import com.haiyangroup.scampus.view.NoticeView;
import com.haiyangroup.library.view.list.XListView;
import com.haiyangroup.library.view.list.adapter.ListViewDataAdapter;
import com.haiyangroup.library.view.list.adapter.ViewHolderBase;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;
import icepick.State;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 公告通知页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class NoticeFragment extends BaseFragment<NoticeView, NoticeComponent, NoticePresenter> implements NoticeView,XListView.IXListViewListener {

    @Inject
    NoticePresenter presenter;
    @InjectView(R.id.xlistview)
    XListView xlistview;

    Boolean isComplete=false;
    private ListViewDataAdapter<NoticeEntity> mListViewAdapter;

    int index = 1;

    @State
    ArrayList<NoticeEntity> NoticeEntities=new ArrayList<>();

    public NoticeFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_notice;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.notice);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
//        NoticeEntity NoticeEntity=new NoticeEntity();
//        NoticeEntity.setAddress("http://www.baidu.com/");
//        NoticeEntity.setTitle("志愿服务，温暖春运");
//        NoticeEntity.setContent("今年的春节异于往年的寒冷");
//        NoticeEntity.setCreateTime("2016/4/12");
//        NoticeEntities.add(NoticeEntity);
//        NoticeEntities.add(NoticeEntity);
//        NoticeEntities.add(NoticeEntity);
//        NoticeEntities.add(NoticeEntity);
//        NoticeEntities.add(NoticeEntity);
//        NoticeEntities.add(NoticeEntity);
//        NoticeEntities.add(NoticeEntity);
        init();
        presenter.getNotice(index);
    }

    @Override
    protected NoticeComponent onCreateNonConfigurationComponent() {
        return DaggerNoticeComponent.builder()
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


    public void init() {
        xlistview.setXListViewListener(this);
        if (mListViewAdapter == null) {
            mListViewAdapter = new ListViewDataAdapter<NoticeEntity>(position -> {
                return new ViewHolderBase<NoticeEntity>() {
                    TextView Notice_title,Notice_content,Notice_time;
                    RelativeLayout Notice;
                    //   public RelativeLayout mLl_message;
                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View view;
                        view = layoutInflater.inflate(R.layout.item_notice, null);
                        Notice_title=(TextView)view.findViewById(R.id.notice_title);
                        Notice_content=(TextView)view.findViewById(R.id.notice_content);
                        Notice_time=(TextView)view.findViewById(R.id.time);
                        Notice=(RelativeLayout)view.findViewById(R.id.notice);
                        return view;
                    }

                    @Override
                    public void showData(int position, final NoticeEntity itemData) {
                        Notice_content.setText(itemData.getContent());
                        Notice_time.setText(itemData.getUpdateDate());
                        Notice_title.setText(itemData.getTitle());

                        Notice.setOnClickListener(v->{
                            WebViewActivity.launch(getBaseContext(), itemData.getAddress(), "公告详情");
                        });

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
        NoticeEntities=new ArrayList<>();
        isComplete = false;
        presenter.getNotice(index);
    }

    public void Refresh() {
        if (mListViewAdapter == null) {
            init();
        }
        mListViewAdapter.getDataList().clear();
        mListViewAdapter.getDataList().addAll(NoticeEntities);
        mListViewAdapter.notifyDataSetChanged();
        xlistview.onLoadComplete(isComplete);
    }

    @Override
    public void onLoadMore() {
        presenter.getNotice(index);
    }

    @Override
    public void onStartPullDown() {

    }

    @Override
    public void showNotice(ArrayList<NoticeEntity> entities) {
        NoticeEntities.addAll(entities);
        index++;
        if(entities.size()< CommonConfig.pageSize){
            isComplete=true;
        }
        Refresh();
    }
}
