package com.haiyangroup.scampus.util;

import android.content.Context;
/**
 * dp和px互相转化的工具类
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue，要转化的dp的数值<br>‘
     * @return int
     */
    public static int dip2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param pxValue，要转化的px的数值<br>‘
     * @return int
     */
    public static int px2dip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
