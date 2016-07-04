package com.haiyangroup.scampus.util.rximageloader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
/**
 * 用LruCache实现内存缓存
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class MemoryCache<T> extends LruCache<T, Bitmap> {

    public MemoryCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(T key, Bitmap b) {
        int size = 0;
        if (b != null) {
            size = b.getRowBytes() * b.getHeight();
        }
        return size;
    }
}
