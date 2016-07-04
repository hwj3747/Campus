package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.CommonConfig;
import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.entity.HomeMenuEntity;
import com.haiyangroup.scampus.entity.NewsEntity;
import com.haiyangroup.scampus.entity.NoticeEntity;
import com.haiyangroup.scampus.model.HomeModel;
import com.haiyangroup.scampus.model.NewsModel;
import com.haiyangroup.scampus.model.NoticeModel;
import com.haiyangroup.scampus.view.HomeView;

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
public class HomePresenter extends BasePresenter<HomeView> {
    @Inject
    HomeModel model;

    @Inject
    NewsModel newsModel;

    @Inject
    NoticeModel noticeModel;





    @Inject
    HomePresenter() {
    }

    private Subscription mNoticeSubscription;

    private Subscription mhomeSubscription;

    private Subscription mNewsSubscription;

    public void getNews(int index,int pageSize){
        if (mNewsSubscription != null && !mNewsSubscription.isUnsubscribed()) {
            return;
        }
        mNewsSubscription=newsModel.getNews(index,pageSize).subscribe(NewsObserver);
    }


    public void getNotice(int index,int pageSize){
        if (mNoticeSubscription != null && !mNoticeSubscription.isUnsubscribed()) {
            return;
        }
        mNoticeSubscription=noticeModel.getNotice(index,pageSize).subscribe(NoticeObserver);
    }

    public void getHomeMenu(){
        mhomeSubscription=model.getHomeMenu().subscribe(homeObserver);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mhomeSubscription != null && !mhomeSubscription.isUnsubscribed()) {
            mhomeSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<ArrayList<HomeMenuEntity>>> homeObserver = new EndObserver<AbsReturn<ArrayList<HomeMenuEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<HomeMenuEntity>> entity) {
            getView().showHomeMenu(entity.getData());
            getBaseView().hideLoading();
        }
    };

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

    private Observer<AbsReturn<ArrayList<NoticeEntity>>> NoticeObserver = new EndObserver<AbsReturn<ArrayList<NoticeEntity>>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<ArrayList<NoticeEntity>> entity) {
            getView().showNotice(entity.getData());
            getBaseView().hideLoading();
        }
    };
}
