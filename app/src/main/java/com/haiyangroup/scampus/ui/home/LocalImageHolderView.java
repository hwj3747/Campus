package com.haiyangroup.scampus.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
/**
 * 首页页面的新闻轮播本地holder实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class LocalImageHolderView implements Holder<Integer> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
//        RxImageLoader.init(context.getApplicationContext());
//        RxImageLoader.getLoaderObservable(imageView, "http://img3.imgtn.bdimg.com/it/u=1183223528,3058066243&fm=21&gp=0.jpg")
//                .subscribe(data1 -> {
//                    if (data1 != null&&data1.bitmap!=null)
//                        Logger.i("bitmap size:" + data1.bitmap.getHeight() * data1.bitmap.getWidth());
//                });
        imageView.setImageResource(data);
    }
}
