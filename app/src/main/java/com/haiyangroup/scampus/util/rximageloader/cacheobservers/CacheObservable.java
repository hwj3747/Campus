package com.haiyangroup.scampus.util.rximageloader.cacheobservers;



import com.haiyangroup.scampus.util.rximageloader.Data;

import rx.Observable;

/**
 * 缓存抽象类，三种缓存方式都继承他，并实现getObservable方法
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public abstract class CacheObservable {
    public abstract Observable<Data> getObservable(String url);
}
