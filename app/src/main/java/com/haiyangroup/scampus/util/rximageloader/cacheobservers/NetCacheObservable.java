package com.haiyangroup.scampus.util.rximageloader.cacheobservers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.haiyangroup.scampus.util.rximageloader.Data;
import com.haiyangroup.scampus.util.rximageloader.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 从网络获取图片
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class NetCacheObservable extends CacheObservable {
    @Override
    public Observable<Data> getObservable(String url) {
        return Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {
                Data data;
                Bitmap bitmap = null;
                InputStream inputStream = null;
                Logger.i("get img on net:" + url);
                try {
                    final URLConnection con = new URL(url).openConnection();
                    con.setDoInput(true);
                    inputStream = con.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                data = new Data(bitmap, url);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
