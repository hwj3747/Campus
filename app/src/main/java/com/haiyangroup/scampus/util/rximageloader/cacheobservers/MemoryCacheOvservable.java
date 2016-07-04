package com.haiyangroup.scampus.util.rximageloader.cacheobservers;


import com.haiyangroup.scampus.util.rximageloader.Data;
import com.haiyangroup.scampus.util.rximageloader.Logger;
import com.haiyangroup.scampus.util.rximageloader.MemoryCache;

import rx.Observable;

/**
 * 从内存取图片
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class MemoryCacheOvservable extends CacheObservable {
    public static final int DEFAULT_CACHE_SIZE = (24 /* MiB */ * 1024 * 1024);
    MemoryCache<String> mCache = new MemoryCache<>(DEFAULT_CACHE_SIZE);

    @Override
    public Observable<Data> getObservable(String url) {
        return Observable.create(subscriber -> {
            Logger.i("search in memory");
            if (!subscriber.isUnsubscribed()) {
                if(url!=null)
                    subscriber.onNext(new Data(mCache.get(url), url));
                subscriber.onCompleted();
            }
        });
    }

    public void putData(Data data) {
        if (data.bitmap != null)
            mCache.put(data.url, data.bitmap);
    }


}
