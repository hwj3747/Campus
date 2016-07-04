package com.haiyangroup.scampus.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.easeui.EaseConstant;
import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.MessageEntity;
import com.haiyangroup.scampus.present.MessagePresenter;
import com.haiyangroup.scampus.ui.chat.ChatActivity;
import com.haiyangroup.scampus.view.MessageView;
import com.haiyangroup.library.utils.SharedPreferencesUtil;
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
 * 消息页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class MessageFragment extends BaseFragment<MessageView, MessageComponent, MessagePresenter> implements MessageView,XListView.IXListViewListener {

    @Inject
    MessagePresenter presenter;
    @InjectView(R.id.xlistview)
    XListView xlistview;

    Boolean isComplete=true;
    private ListViewDataAdapter<MessageEntity> mListViewAdapter;

    @State
    ArrayList<MessageEntity> messageEntities=new ArrayList<>();
    
    public MessageFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_message;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.message);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void onViewInit() {
        MessageEntity messageEntity=new MessageEntity();
        messageEntity.setName("陈老师");
        messageEntity.setMessage("你好");
        messageEntity.setCount(4);
        messageEntity.setTime(XListView.topTimeFormat("2016-4-26 7:00:00"));
        messageEntities.add(messageEntity);
        messageEntities.add(messageEntity);
        messageEntities.add(messageEntity);
        messageEntities.add(messageEntity);
        messageEntities.add(messageEntity);
        messageEntities.add(messageEntity);
        messageEntities.add(messageEntity);

        init();
        Refresh();
    }

    @Override
    protected MessageComponent onCreateNonConfigurationComponent() {
        return DaggerMessageComponent.builder()
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
            mListViewAdapter = new ListViewDataAdapter<MessageEntity>(position -> {
                return new ViewHolderBase<MessageEntity>() {
                    ImageView headImg;
                    TextView name,message,time,messageCount;
                    RelativeLayout messageLayout;
                    //   public RelativeLayout mLl_message;
                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View view;
                        view = layoutInflater.inflate(R.layout.item_message, null);
                        headImg=(ImageView)view.findViewById(R.id.head_image);
                        messageCount=(TextView)view.findViewById(R.id.message_count);
                        name=(TextView)view.findViewById(R.id.name);
                        message=(TextView)view.findViewById(R.id.message_content);
                        time=(TextView)view.findViewById(R.id.time);
                        messageLayout=(RelativeLayout)view.findViewById(R.id.message_layout);
                        return view;
                    }

                    @Override
                    public void showData(int position, final MessageEntity itemData) {
                        name.setText(itemData.getName());
                        message.setText(itemData.getMessage());
                        time.setText(itemData.getTime());
                        messageCount.setTag(itemData.getCount()+"");

//                        news_pitute.setTag(itemData.getPic());
//                        RxImageLoader.getLoaderObservable(news_pitute, itemData.getPic(), "").subscribe();

                        messageLayout.setOnClickListener(v -> {
                            Intent intent = new Intent(getBaseContext(),ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, SharedPreferencesUtil.getInstance(getBaseContext()).getString("touser"));
                            getBaseContext().startActivity(intent);
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
        Refresh();
    }

    public void Refresh() {
        if (mListViewAdapter == null) {
            init();
        }
        mListViewAdapter.getDataList().clear();
        mListViewAdapter.getDataList().addAll(messageEntities);
        mListViewAdapter.notifyDataSetChanged();
        xlistview.onLoadComplete(isComplete);
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onStartPullDown() {

    }
}
