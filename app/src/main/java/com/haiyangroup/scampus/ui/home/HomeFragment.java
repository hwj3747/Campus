package com.haiyangroup.scampus.ui.home;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.HomeMenuEntity;
import com.haiyangroup.scampus.entity.NewsEntity;
import com.haiyangroup.scampus.entity.NoticeEntity;
import com.haiyangroup.scampus.present.HomePresenter;
import com.haiyangroup.scampus.present.NewsPresenter;
import com.haiyangroup.scampus.present.NoticePresenter;
import com.haiyangroup.scampus.ui.agenda_list.AgendaListActivity;
import com.haiyangroup.scampus.ui.approval.ApprovalActivity;
import com.haiyangroup.scampus.ui.attendance.AttendanceActivity;
import com.haiyangroup.scampus.ui.attendance_teacher.AttendanceTeacherActivity;
import com.haiyangroup.scampus.ui.calendar.CalendarActivity;
import com.haiyangroup.scampus.ui.card.CardActivity;
import com.haiyangroup.scampus.ui.filecabinet.FileCabinetActivity;
import com.haiyangroup.scampus.ui.homework.HomeworkActivity;
import com.haiyangroup.scampus.ui.news.NewsActivity;
import com.haiyangroup.scampus.ui.notice.NoticeActivity;
import com.haiyangroup.scampus.ui.score.ScoreActivity;
import com.haiyangroup.scampus.ui.webview.WebViewActivity;
import com.haiyangroup.scampus.util.DensityUtil;
import com.haiyangroup.scampus.view.HomeView;
import com.haiyangroup.scampus.view.NewsView;
import com.haiyangroup.scampus.view.NoticeView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.haiyangroup.scampus.common.App.getAppComponent;



/**
 * 首页页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class HomeFragment extends BaseFragment<HomeView, HomeComponent, HomePresenter> implements HomeView,ViewPager.OnPageChangeListener, OnItemClickListener,View.OnClickListener {

    @Inject
    HomePresenter presenter;

    @InjectView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @InjectView(R.id.news_title)
    TextView newsTitle;
    @InjectView(R.id.notice_content)
    TextView noticeContent;
    @InjectView(R.id.home_content)
    RelativeLayout homeContent;
    Timer timer;
    Subscription timeSubcription;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    ArrayList<String> homeList = new ArrayList<>();
    ArrayList<NoticeEntity> noticeList = new ArrayList<>();

    ArrayList<String> imagesList = new ArrayList<>();
    ArrayList<String> newsList = new ArrayList<>();
    ArrayList<String> addressList = new ArrayList<>();

    static int currentNotice = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.home);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);


    }


    @Override
    protected void onViewInit() {
        presenter.getHomeMenu();
        presenter.getNews(1, 3);
        presenter.getNotice(1, 3);

    }

    @Override
    protected HomeComponent onCreateNonConfigurationComponent() {
        return DaggerHomeComponent.builder()
                .appComponent(getAppComponent(mActivity))
                .build();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save state of all @State annotated members
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

//
//    private class MyTimerTask extends TimerTask {
//        @Override
//        public void run() {
//            Message message = new Message();
//            message.what = 1;
//            handler.sendMessage(message);
//        }
//    }
//
//    ;
//
//    final Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    if (currentNotice>= noticeList.size())
//                        currentNotice = 0;
//                    noticeContent.setText(noticeList.get(currentNotice));
//                    currentNotice++;
//            }
//        }
//    };

    //定时器切换公告
    public void initNotice() {
//        if(timer==null)
//            timer = new Timer();
//        MyTimerTask task = new MyTimerTask();
//        timer.schedule(task, 0, 2000);
        if(timeSubcription==null||timeSubcription.isUnsubscribed()){
            Observable
                    .interval(0,2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                    aLong -> {
                        if (currentNotice>= noticeList.size())
                        currentNotice = 0;
                        noticeContent.setText(noticeList.get(currentNotice).getTitle());
                        currentNotice++;}
            );
        }
        noticeContent.setOnClickListener(v->{
                WebViewActivity.launch(getBaseContext(),noticeList.get(currentNotice-1).getAddress(),"公告");
        });
    }

    /**
     * 初始化主页的菜单选项
     * 动态生成每个textview，判断需要加入的菜单添加这个菜单
     * @return void
     */
    public void initFragment() {

        int count = homeList.size();
        int column;
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) getBaseContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        //屏幕宽度
        int width = dm.widthPixels;
        //平均宽度
        int aveWidth = width / 3;

        if (count % 3 == 0)
            column = count / 3;
        else
            column = count / 3 + 1;
        for (int i = 1; i <= column; i++) {

            for (int j = 1; j <= 3; j++) {

                TextView tx = new TextView(getBaseContext());
                tx.setId((i - 1) * 3 + j);
                tx.setOnClickListener(this);
                if (count % 3 < j && i == column) {

                } else {
                    tx.setText(homeList.get((i - 1) * 3 + j - 1));
                    Drawable drawable=null;
                    switch (homeList.get((i - 1) * 3 + j - 1)) {
                        case "学校新闻": {
                            drawable = getResources().getDrawable(R.drawable.icon_home_news);
                            break;
                        }
                        case "公告通知": {
                            drawable = getResources().getDrawable(R.drawable.icon_home_notice);
                            break;
                        }
                        case "学生考勤": {
                            drawable = getResources().getDrawable(R.drawable.icon_home_check);
                            break;
                        }
                        case "成绩查询": {
                            drawable = getResources().getDrawable(R.drawable.icon_home_results);
                            break;
                        }
                        case "校历": {
                            drawable = getResources().getDrawable(R.drawable.icon_home_calendar);
                            break;
                        }
                        case "请假审批": {
                            drawable = getResources().getDrawable(R.drawable.icon_home_leave);
                            break;
                        }
                        case "作业布置": {
                            drawable = getResources().getDrawable(R.drawable.icon_home_homework);
                            break;
                        }
                        case "个人文件柜": {
                            drawable = getResources().getDrawable(R.drawable.icon_filing_cabinet);
                            break;
                        }
                        case "教师考勤": {
                            drawable = getResources().getDrawable(R.drawable.icon_teacher_attendance);
                            break;
                        }
                        case "一卡通查询": {
                            drawable = getResources().getDrawable(R.drawable.icon_card2);
                            break;
                        }
                        case "日程安排": {
                            drawable = getResources().getDrawable(R.drawable.icon_schedule);
                            break;
                        }
                    }
                    drawable.setBounds(0, 0, DensityUtil.dip2px(getBaseContext(), 25), DensityUtil.dip2px(getBaseContext(), 29));
                    tx.setCompoundDrawables(null, drawable, null, null);
                }

                tx.setBackgroundDrawable(getBaseContext().
                        getResources().getDrawable(R.drawable.border_bg));
                //相对布局参数
                RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(aveWidth, aveWidth);
                //文字对齐方式
                tx.setGravity(Gravity.CENTER);
                tx.setPadding(aveWidth / 6, aveWidth / 6, aveWidth / 6, aveWidth / 6);
                //字体样式
                tx.setTextAppearance(getBaseContext(), R.style.HomeTableText);

                if (i == 1 && j == 1) {
                } else if (i == 1 && j != 1) {
                    rp.addRule(RelativeLayout.RIGHT_OF, (i - 1) * 3 + j - 1);
                } else if (i != 1 && j == 1) {
                    rp.addRule(RelativeLayout.BELOW, (i - 2) * 3 + j);
                } else {
                    rp.addRule(RelativeLayout.BELOW, (i - 2) * 3 + j);
                    rp.addRule(RelativeLayout.RIGHT_OF, (i - 1) * 3 + j - 1);

                }

                tx.setLayoutParams(rp);
                homeContent.addView(tx);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (((TextView)v).getText().toString()){
            case "学校新闻":
                NewsActivity.launch(getBaseContext());
                break;
            case "公告通知":
                NoticeActivity.launch(getBaseContext());
                break;
            case "成绩查询":
                ScoreActivity.launch(getBaseContext());
                break;
            case "请假审批":
                ApprovalActivity.launch(getBaseContext());
                break;
            case "校历":
                CalendarActivity.launch(getBaseContext());
                break;
            case "学生考勤":
                AttendanceActivity.launch(getBaseContext());
                break;
            case "作业布置":
                HomeworkActivity.launch(getBaseContext());
                break;
            case "个人文件柜":
                FileCabinetActivity.launch(getBaseContext());
                break;
            case "教师考勤":
                AttendanceTeacherActivity.launch(getBaseContext());
                break;
            case "一卡通查询":
                CardActivity.launch(getBaseContext());
                break;
            case "日程安排":
                AgendaListActivity.launch(getBaseContext());
                break;

        }
    }


    /**
     * 初始化广告栏
     * @return void
     */
    public void initBanner() {
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms.ZoomOutTranformer");//设置图片切换动画
            ABaseTransformer transforemer = (ABaseTransformer) cls.newInstance();
            convenientBanner.getViewPager().setPageTransformer(true, transforemer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        convenientBanner.setOnPageChangeListener(this);
        loadTestDatas();
        //本地图片例子
//        convenientBanner.setPages(() -> {
//            return new LocalImageHolderView();
//        }, localImages)
//                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示�?不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnItemClickListener(this);
//        convenientBanner.startTurning(2000);//加载本地图片

        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                NetworkImageHolderView networkImageHolderView = new NetworkImageHolderView();
                return networkImageHolderView;
            }
        }, imagesList)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(this);
        convenientBanner.startTurning(2000);//加载网络图片
    }

    /**
     * 加载本地图片
     * @return void
     */
    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 2; position++)
            localImages.add(getResId("banner_picture_" + position, R.drawable.class));
    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public void onItemClick(int position) {//广告点击事件
        WebViewActivity.launch(getBaseContext(),addressList.get(position),"新闻详情");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        newsTitle.setText(newsList.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (convenientBanner != null)
            convenientBanner.startTurning(2000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        if (convenientBanner != null)
            convenientBanner.stopTurning();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null)
            timer.cancel();
        if(timeSubcription!= null && !timeSubcription.isUnsubscribed()){
            timeSubcription.unsubscribe();
        }
    }

    @Override
    public void showHomeMenu(ArrayList<HomeMenuEntity> entities) {
        for(HomeMenuEntity entity:entities){
            homeList.add(entity.getName());
        }
        initFragment();
    }

    @Override
    public void showNews(ArrayList<NewsEntity> entities) {
        for(NewsEntity entity:entities){
            newsList.add(entity.getTitle());
            addressList.add(entity.getAddress());
            imagesList.add(entity.getCover());
        }
        initBanner();
    }

    @Override
    public void showNotice(ArrayList<NoticeEntity> entities) {
        noticeList=entities;
        initNotice();
    }
}
