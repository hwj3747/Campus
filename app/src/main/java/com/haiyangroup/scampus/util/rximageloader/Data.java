package com.haiyangroup.scampus.util.rximageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 保存图片和URL的类
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class Data {
    public Bitmap bitmap;
    public String url;
    private boolean isAvailable;

    public Data(Bitmap bitmap, String url) {
        this.bitmap = bitmap;
        this.url = url;
    }

    public Data(File f, String url) {
        if (f != null && f.exists()) {
            this.url = url;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAvailable() {
        isAvailable = url != null && bitmap != null;
        return isAvailable;
    }
}
