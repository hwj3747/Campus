package com.haiyangroup.scampus.ui.schedule;

/**
 * 课表页面自定义纵向ScrollView滑动接口实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public interface MyScrollViewListener {
    void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);
}
