package com.haiyangroup.scampus.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.util.rximageloader.RxImageLoader;

/**
 * 首页页面的新闻轮播网络holder实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context,int position, String data) {
        imageView.setImageResource(R.drawable.ic_default_adimage);
        imageView.setTag(data);
//        ImageLoader.getInstance().displayImage(data,imageView);
       // RxImageLoader.init(context);
        RxImageLoader.getLoaderObservable(imageView,data,"").subscribe();
    }
}
