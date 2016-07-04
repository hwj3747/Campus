package com.haiyangroup.scampus.ui.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.common.CommonConfig;
import com.haiyangroup.scampus.entity.NewsEntity;
import com.haiyangroup.scampus.present.NewsPresenter;
import com.haiyangroup.scampus.ui.webview.WebViewActivity;
import com.haiyangroup.scampus.util.rximageloader.RxImageLoader;
import com.haiyangroup.scampus.view.NewsView;
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
 * 学校新闻页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class NewsFragment extends BaseFragment<NewsView, NewsComponent, NewsPresenter> implements NewsView,XListView.IXListViewListener {

    @Inject
    NewsPresenter presenter;
    @InjectView(R.id.xlistview)
    XListView xlistview;

    Boolean isComplete=false;
    private ListViewDataAdapter<NewsEntity> mListViewAdapter;

    int index = 1;

    @State
    ArrayList<NewsEntity> newsEntities=new ArrayList<>();

    public NewsFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.news);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

    }


    @Override
    protected void onViewInit() {
//        NewsEntity newsEntity=new NewsEntity();
//        newsEntity.setAddress("http://www.baidu.com/");
//        newsEntity.setTitle("志愿服务，温暖春运");
//        newsEntity.setContent("今年的春节异于往年的寒冷");
//        newsEntity.setCreateTime("2016/4/12");
//        newsEntity.setPic("http://img2.3lian.com/2014/f2/37/d/40.jpg");
//        newsEntities.add(newsEntity);
//        newsEntities.add(newsEntity);
//        newsEntities.add(newsEntity);
//        newsEntities.add(newsEntity);
//        newsEntities.add(newsEntity);
//        newsEntities.add(newsEntity);
//        newsEntities.add(newsEntity);
        init();
        presenter.getNews(index,CommonConfig.pageSize);
    }

    @Override
    protected NewsComponent onCreateNonConfigurationComponent() {
        return DaggerNewsComponent.builder()
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
            mListViewAdapter = new ListViewDataAdapter<NewsEntity>(position -> {
                return new ViewHolderBase<NewsEntity>() {
                    ImageView news_pitute;
                    TextView news_title,news_content,news_time;
                    RelativeLayout news;
                    //   public RelativeLayout mLl_message;
                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View view;
                            view = layoutInflater.inflate(R.layout.item_news, null);
                        news_pitute=(ImageView)view.findViewById(R.id.news_picture);
                        news_title=(TextView)view.findViewById(R.id.news_title);
                        news_content=(TextView)view.findViewById(R.id.news_content);
                        news_time=(TextView)view.findViewById(R.id.time);
                        news=(RelativeLayout)view.findViewById(R.id.news);
                        return view;
                    }

                    @Override
                    public void showData(int position, final NewsEntity itemData) {
                        news_content.setText(itemData.getContent());
                        news_time.setText(itemData.getUpdateDate());
                        news_title.setText(itemData.getTitle());

                        news_pitute.setTag(itemData.getCover());
                        RxImageLoader.getLoaderObservable(news_pitute,itemData.getCover(),"").subscribe();

                        news.setOnClickListener(v->{
                            WebViewActivity.launch(getBaseContext(), itemData.getAddress(),"新闻详情");
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
        newsEntities=new ArrayList<>();
        isComplete = false;
        presenter.getNews(index,CommonConfig.pageSize);
    }

    public void Refresh() {
        if (mListViewAdapter == null) {
            init();
        }
        mListViewAdapter.getDataList().clear();
        mListViewAdapter.getDataList().addAll(newsEntities);
        mListViewAdapter.notifyDataSetChanged();
        xlistview.onLoadComplete(isComplete);
    }

    @Override
    public void onLoadMore() {

        presenter.getNews(index,CommonConfig.pageSize);
    }

    @Override
    public void onStartPullDown() {

    }

    @Override
    public void showNews(ArrayList<NewsEntity> entities) {
        newsEntities.addAll(entities);
        index++;
        if(entities.size()< CommonConfig.pageSize){
            isComplete=true;
        }
        Refresh();
        Log.i("index",index+"");
    }
}
