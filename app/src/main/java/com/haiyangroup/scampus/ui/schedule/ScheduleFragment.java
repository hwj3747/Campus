package com.haiyangroup.scampus.ui.schedule;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.R;
import com.haiyangroup.scampus.base.BaseFragment;
import com.haiyangroup.scampus.entity.ScheduleEntity;
import com.haiyangroup.scampus.present.SchedulePresenter;
import com.haiyangroup.scampus.ui.rollcall.RollcallActivity;
import com.haiyangroup.scampus.util.TimeUtil;
import com.haiyangroup.scampus.view.ScheduleView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import icepick.Icepick;

import static com.haiyangroup.scampus.common.App.getAppComponent;


/**
 * 课表页面的fragment实现
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class ScheduleFragment extends BaseFragment<ScheduleView, ScheduleComponent, SchedulePresenter> implements ScheduleView, MyScrollViewListener, MyHorizontalScrollViewListener, View.OnClickListener {

    @Inject
    SchedulePresenter presenter;
    @InjectView(R.id.test_empty)
    TextView empty;
    @InjectView(R.id.test_monday_course)
    TextView monColum;
    @InjectView(R.id.test_tuesday_course)
    TextView tueColum;
    @InjectView(R.id.test_wednesday_course)
    TextView wedColum;
    @InjectView(R.id.test_thursday_course)
    TextView thrusColum;
    @InjectView(R.id.test_friday_course)
    TextView friColum;
    @InjectView(R.id.test_saturday_course)
    TextView satColum;
    @InjectView(R.id.test_sunday_course)
    TextView sunColum;
    @InjectView(R.id.section1)
    TextView section1;
    @InjectView(R.id.section2)
    TextView section2;
    @InjectView(R.id.section3)
    TextView section3;
    @InjectView(R.id.section4)
    TextView section4;
    @InjectView(R.id.section5)
    TextView section5;
    @InjectView(R.id.section6)
    TextView section6;
    @InjectView(R.id.section7)
    TextView section7;
    @InjectView(R.id.section8)
    TextView section8;
    @InjectView(R.id.section9)
    TextView section9;
    @InjectView(R.id.section10)
    TextView section10;
    @InjectView(R.id.section11)
    TextView section11;
    @InjectView(R.id.section12)
    TextView section12;
    @InjectView(R.id.section_scrollview)
    MyScrollView scrollView4;
    @InjectView(R.id.course_scrollview)
    MyHorizontalScrollView scrollView2;
    @InjectView(R.id.scroll_body)
    MyScrollView scrollView3;
    @InjectView(R.id.week_scrollview)
    MyHorizontalScrollView scrollView1;
    @InjectView(R.id.test_course_rl)
    RelativeLayout course_table_layout;

    /**
     * 屏幕宽度
     **/
    protected int screenWidth;
    /**
     * 课程格子平均宽度
     **/
    protected int aveWidth;
    /**
     * 课程格子平均高度
     **/
    protected int gridHeight;

    ArrayList<TextView> course=new ArrayList<>();
    public ScheduleFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_schedule;
    }

    @Override
    protected View getLoadingTargetView() {
        return findById(R.id.schedule);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        initSchedule();
        presenter.getSchedule(SharedPreferencesUtil.getInstance(getBaseContext()).getString("week"));
        setWeekDay(Integer.parseInt(SharedPreferencesUtil.getInstance(getBaseContext()).getString("week")));
    }


    @Override
    protected void onViewInit() {

    }

    @Override
    protected ScheduleComponent onCreateNonConfigurationComponent() {
        return DaggerScheduleComponent.builder()
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
    public void onResume() {
        super.onResume();
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

    private void initSchedule() {//初始化课表
        scrollView1.setScrollViewListener(this);
        scrollView2.setScrollViewListener(this);
        scrollView3.setScrollViewListener(this);
        scrollView4.setScrollViewListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) getBaseContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        //屏幕宽度
        int width = dm.widthPixels;
        //平均宽度
        aveWidth = width / 8;
        //第一个空白格子设置为25宽

        section1.setWidth(aveWidth);
        section2.setWidth(aveWidth);
        section3.setWidth(aveWidth);
        section4.setWidth(aveWidth);
        section5.setWidth(aveWidth);
        section6.setWidth(aveWidth);
        section7.setWidth(aveWidth);
        section8.setWidth(aveWidth);
        section9.setWidth(aveWidth);
        section10.setWidth(aveWidth);
        section11.setWidth(aveWidth);
        section12.setWidth(aveWidth);//设置列的宽度

        empty.setWidth(aveWidth);
        monColum.setWidth(aveWidth * 5 / 3);
        tueColum.setWidth(aveWidth * 5 / 3);
        wedColum.setWidth(aveWidth * 5 / 3);
        thrusColum.setWidth(aveWidth * 5 / 3);
        friColum.setWidth(aveWidth * 5 / 3);
        satColum.setWidth(aveWidth * 5 / 3);
        sunColum.setWidth(aveWidth * 5 / 3);//设置行的宽度

        section1.setText(Html.fromHtml("<font color='#939696'>8:00</font><br><font color= '#000000'>1</font>"));
        section2.setText(Html.fromHtml("<font color='#939696'>9:00</font><br><font color= '#000000'>2</font>"));
        section3.setText(Html.fromHtml("<font color='#939696'>10:00</font><br><font color= '#000000'>3</font>"));
        section4.setText(Html.fromHtml("<font color='#939696'>11:00</font><br><font color= '#000000'>4</font>"));
        section5.setText(Html.fromHtml("<font color='#939696'>14:00</font><br><font color= '#000000'>5</font>"));
        section6.setText(Html.fromHtml("<font color='#939696'>15:00</font><br><font color= '#000000'>6</font>"));
        section7.setText(Html.fromHtml("<font color='#939696'>16:00</font><br><font color= '#000000'>7</font>"));
        section8.setText(Html.fromHtml("<font color='#939696'>17:00</font><br><font color= '#000000'>8</font>"));
        section9.setText(Html.fromHtml("<font color='#939696'>18:00</font><br><font color= '#000000'>9</font>"));
        section10.setText(Html.fromHtml("<font color='#939696'>19:00</font><br><font color= '#000000'>10</font>"));
        section11.setText(Html.fromHtml("<font color='#939696'>20:00</font><br><font color= '#000000'>11</font>"));
        section12.setText(Html.fromHtml("<font color='#939696'>21:00</font><br><font color= '#000000'>12</font>"));

        this.screenWidth = width;
        int height = dm.heightPixels;
        gridHeight = height / 9;

        empty.setHeight(gridHeight * 3 / 5);
        monColum.setHeight(gridHeight * 3 / 5);
        tueColum.setHeight(gridHeight * 3 / 5);
        wedColum.setHeight(gridHeight * 3 / 5);
        thrusColum.setHeight(gridHeight * 3 / 5);
        friColum.setHeight(gridHeight * 3 / 5);
        satColum.setHeight(gridHeight * 3 / 5);
        sunColum.setHeight(gridHeight * 3 / 5);//设置行的高度

        section1.setHeight(gridHeight);
        section2.setHeight(gridHeight);
        section3.setHeight(gridHeight);
        section4.setHeight(gridHeight);
        section5.setHeight(gridHeight);
        section6.setHeight(gridHeight);
        section7.setHeight(gridHeight);
        section8.setHeight(gridHeight);
        section9.setHeight(gridHeight);
        section10.setHeight(gridHeight);
        section11.setHeight(gridHeight);
        section12.setHeight(gridHeight);//设置列的高度


        //设置课表界面
        //动态生成12 * maxCourseNum个textview
        for (int i = 1; i <= 12; i++) {

            for (int j = 1; j <= 7; j++) {

                TextView tx = new TextView(getBaseContext());
                tx.setId((i - 1) * 7 + j);
                //除了最后一列，都使用course_text_view_bg背景（最后一列没有右边框）
                if (j < 8)
                    tx.setBackgroundDrawable(getBaseContext().
                            getResources().getDrawable(R.drawable.course_text_view_bg));
                else
                    tx.setBackgroundDrawable(getBaseContext().
                            getResources().getDrawable(R.drawable.course_table_last_colum));
                //相对布局参数
//				RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
//						aveWidth * 33 / 32 + 1,
//						gridHeight);
                RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
                        aveWidth * 5 / 3,
                        gridHeight);
                //文字对齐方式
                tx.setGravity(Gravity.CENTER);
                //字体样式
                tx.setTextAppearance(getBaseContext(), R.style.courseTableText);
                //如果是第一列，需要设置课的序号（1 到 12）
                if (j == 1) {
                    tx.setText("");
                    if (i == 1)
                        rp.addRule(RelativeLayout.BELOW, R.id.test_monday_course);
                    else
                        rp.addRule(RelativeLayout.BELOW, (i - 2) * 7 + j);
                } else {
                    rp.addRule(RelativeLayout.RIGHT_OF, (i - 1) * 7 + j - 1);
                    rp.addRule(RelativeLayout.ALIGN_TOP, (i - 1) * 7 + j - 1);
                    tx.setText("");
                }

                tx.setLayoutParams(rp);
                course_table_layout.addView(tx);
            }
        }
    }

    @Override
    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView == scrollView3) {
            scrollView4.scrollTo(x, y);
        } else if (scrollView == scrollView4) {
            scrollView3.scrollTo(x, y);
        }
    }

    @Override
    public void onScrollChanged(MyHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView == scrollView1) {
            scrollView2.scrollTo(x, y);
        } else if (scrollView == scrollView2) {
            scrollView1.scrollTo(x, y);
        }
    }

    private void setCourse(String text,int week,int section) {
        //五种颜色的背景
        int[] background = {R.drawable.course_info_blue, R.drawable.course_info_green,
                R.drawable.course_info_red, R.drawable.course_info_red,
                R.drawable.course_info_yellow};
        // 添加课程信息

        TextView courseInfo = new TextView(getBaseContext());
        courseInfo.setOnClickListener(this);
        courseInfo.setText(text);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                aveWidth * 5/3,
                gridHeight*2);

        rlp.topMargin = (section-1)*2 * gridHeight;//大节来算一大节=2小节
//		rlp.leftMargin = 1+4*(aveWidth * 33 / 32 + 1);
        rlp.leftMargin = (week-1) * (aveWidth * 5 / 3);//
        // 偏移由这节课是星期几决定
        rlp.addRule(RelativeLayout.RIGHT_OF, R.id.test_empty);
        //字体剧中
        courseInfo.setGravity(Gravity.CENTER);
        // 设置一种背景
//        courseInfo.setBackgroundResource(background[(int)(Math.random()*5)]);
        courseInfo.setBackgroundColor(Color.argb(255,(int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200)));
        courseInfo.setTextSize(12);
        courseInfo.setLayoutParams(rlp);
        courseInfo.setTextColor(Color.WHITE);
        //设置不透明度
        //courseInfo.getBackground().setAlpha(222);
        course_table_layout.addView(courseInfo);
        course.add(courseInfo);
    }

    @Override
    public void onClick(View v) {
        RollcallActivity.launch(getBaseContext(),"rollcall","","计算机一班");
    }

    public void setWeek(String week){
        for(TextView tv:course) {
            course_table_layout.removeView(tv);
        }
//        if(week.equals("第1周")){
//            setCourse("软件工程", 5, 1);//week1-7，section1-6
//            setCourse("计算机技术",2,3);
//            setCourse("UI入门",1,1);
//            setCourse("计算机组成原理(2号楼404)",2,4);
//            setCourse("操作系统",5,2);
//            setCourse("大数据处理",7,5);
//            setCourse("Java程序设计",3,2);
//        }
//        else {
//            setCourse("软件工程", 1, 1);//week1-7，section1-6
//            setCourse("计算机技术", 1, 2);
//            setCourse("UI入门", 3, 4);
//            setCourse("计算机组成原理(2号楼404)", 2, 4);
//            setCourse("操作系统", 2, 2);
//            setCourse("大数据处理", 4, 1);
//            setCourse("Java程序设计", 4, 2);
//        }
        week=week.replace("第","");
        week=week.replace("周","");
        presenter.getSchedule(week);

        int weekday=Integer.parseInt(week);
        setWeekDay(weekday);

    }

    private void setWeekDay(int weekday){
        int CurrentWeek=Integer.parseInt(SharedPreferencesUtil.getInstance(getBaseContext()).getString("week"));
        ArrayList<String> weekList= TimeUtil.currentWeek(weekday - CurrentWeek);
        monColum.setText("周一\n"+ weekList.get(0));
        tueColum.setText("周二\n"+ weekList.get(1));
        wedColum.setText("周三\n"+ weekList.get(2));
        thrusColum.setText("周四\n"+ weekList.get(3));
        friColum.setText("周五\n"+ weekList.get(4));
        satColum.setText("周六\n"+ weekList.get(5));
        sunColum.setText("周日\n"+ weekList.get(6));

        monColum.setTextColor(getBaseContext().getResources().getColor(R.color.black));
        tueColum.setTextColor(getBaseContext().getResources().getColor(R.color.black));
        wedColum.setTextColor(getBaseContext().getResources().getColor(R.color.black));
        thrusColum.setTextColor(getBaseContext().getResources().getColor(R.color.black));
        friColum.setTextColor(getBaseContext().getResources().getColor(R.color.black));
        satColum.setTextColor(getBaseContext().getResources().getColor(R.color.black));
        sunColum.setTextColor(getBaseContext().getResources().getColor(R.color.black));

        if(weekday==CurrentWeek) {
            int currentWeek = TimeUtil.currentWeekOfDay();
            if (currentWeek == 1)
                sunColum.setTextColor(getBaseContext().getResources().getColor(R.color.color_primary_green));
            else if (currentWeek == 2)
                monColum.setTextColor(getBaseContext().getResources().getColor(R.color.color_primary_green));
            else if (currentWeek == 3)
                tueColum.setTextColor(getBaseContext().getResources().getColor(R.color.color_primary_green));
            else if (currentWeek == 4)
                wedColum.setTextColor(getBaseContext().getResources().getColor(R.color.color_primary_green));
            else if (currentWeek == 5)
                thrusColum.setTextColor(getBaseContext().getResources().getColor(R.color.color_primary_green));
            else if (currentWeek == 6)
                friColum.setTextColor(getBaseContext().getResources().getColor(R.color.color_primary_green));
            else
                satColum.setTextColor(getBaseContext().getResources().getColor(R.color.color_primary_green));
        }
    }

    @Override
    public void showData(ArrayList<ScheduleEntity> entities) {
        for(TextView tv:course) {
            course_table_layout.removeView(tv);
        }
        for(ScheduleEntity entity:entities){
            Log.i("week",entity.getCourseId()+entity.getClassroomId()+entity.getTeacherId());
            setCourse(entity.getCourseId()+"\n"+entity.getClassroomId()+"\n"+entity.getTeacherId(),Integer.parseInt(entity.getWeekday()),Integer.parseInt(entity.getCourseOrder()));
        }

    }
}
