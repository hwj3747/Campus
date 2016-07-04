package com.haiyangroup.scampus.common;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * 实现线程切换，被观察者在io线程进行 ，观察者在主线程
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public interface SchedulerProvider {
    <T> Observable.Transformer<T, T> applySchedulers();

    SchedulerProvider DEFAULT = new SchedulerProvider() {
        @Override public <T> Observable.Transformer<T, T> applySchedulers() {
            return observable -> observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };
}
