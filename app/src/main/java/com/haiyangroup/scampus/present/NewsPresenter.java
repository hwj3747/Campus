package com.haiyangroup.scampus.present;

import android.util.Log;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.CommonConfig;
import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.NewsEntity;
import com.haiyangroup.scampus.model.NewsModel;
import com.haiyangroup.scampus.view.NewsView;

import java.util.ArrayList;

import javax.inject.Inject;

import compartment.BasePresenter;
import rx.Observer;
import rx.Subscription;

/**
 * 实现MVP设计模式的present指挥者，用来控制model与view的交互
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@MyScope
public class NewsPresenter extends BasePresenter<NewsView> {
    @Inject
    NewsModel model;

    @Inject
    NewsPresenter() {
    }

    private Subscription mNewsSubscription;

    public void getNews(int index,int pageSize){
        if (mNewsSubscription != null && !mNewsSubscription.isUnsubscribed()) {
            return;
        }
        mNewsSubscription=model.getNews(index,pageSize).subscribe(NewsObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mNewsSubscription != null && !mNewsSubscription.isUnsubscribed()) {
            mNewsSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<NewsEntity>>> NewsObserver = new EndObserver<AbsReturn<ArrayList<NewsEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<NewsEntity>> entity) {
            getView().showNews(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
