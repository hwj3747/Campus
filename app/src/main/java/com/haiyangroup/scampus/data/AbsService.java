package com.haiyangroup.scampus.data;


import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.common.CommonConfig;
import com.haiyangroup.scampus.entity.AttendanceEntity;
import com.haiyangroup.scampus.entity.AttendanceInfoEntity;
import com.haiyangroup.scampus.entity.CalendarEntity;
import com.haiyangroup.scampus.entity.ConditionResultEntity;
import com.haiyangroup.scampus.entity.CurrentWeekEntity;
import com.haiyangroup.scampus.entity.HomeMenuEntity;
import com.haiyangroup.scampus.entity.HomeworkEntity;
import com.haiyangroup.scampus.entity.LoginReturnEntity;
import com.haiyangroup.scampus.entity.NewsEntity;
import com.haiyangroup.scampus.entity.NoteEntity;
import com.haiyangroup.scampus.entity.NoticeEntity;
import com.haiyangroup.scampus.entity.RollCallListForm;
import com.haiyangroup.scampus.entity.ScheduleEntity;
import com.haiyangroup.scampus.entity.ScoreEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
/**
 * 用retrofit从服务端取数据，并提供各种API供model调用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AbsService {


    private static final String FORUM_SERVER_URL = "http://10.0.0.100:8080/smartCampus/a";
    private AbsApi mAbsApi;

    /**
     * 初构造函数，初始化网络请求配置
     */
    public AbsService() {

        RequestInterceptor requestInterceptor = request -> {
            request.addHeader("Accept", "text/html");
            //request.addHeader("token",ConfigUtil.getToken());
            String session=SharedPreferencesUtil.getInstance(App.getInstance().getBaseContext()).getString("session");
            request.addHeader("os","Android");
            request.addHeader("ver","1");
            request.addHeader("jeesite.session.id",session);
        };


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(FORUM_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                        // .setClient(new OkClient(new OkHttpClient()))
                .build();

        mAbsApi = restAdapter.create(AbsApi.class);
    }

    public AbsApi getApi() {
        return mAbsApi;
    }

    /**
     * 利用retrofit框架提供服务器接口供model层调用
     * @return void
     */
    public interface AbsApi {


        @GET("/school/calendar/schoolCalendar/getCalendar;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<CalendarEntity>>>
        getCalendar(@Path("session") String session);//获取校历

        @FormUrlEncoded
        @POST("/login?__ajax=true")
        Observable<LoginReturnEntity>
        Login(@Field("username") String username,@Field("password") String password,@Field("mobileLogin") Boolean mobileLogin);//登录

        @GET("/school/news/schoolNews/getNews;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<NewsEntity>>>
        getNews(@Query("index") int index,@Query("pageSize") int pageSize,@Path("session") String session);//获取新闻

        @GET("/school/notice/schoolNotice/getNotice;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<NoticeEntity>>>
        getNotice(@Query("index") int index,@Query("pageSize") int pageSize,@Path("session") String session);//获取公告

        @FormUrlEncoded
        @POST("/school/tips/schoolTips/saveTips;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<DefaultData>>
        feedback(@Field("content") String content,@Path("session") String session);//意见反馈

        @FormUrlEncoded
        @POST("/sys/user/modifyPassword;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<DefaultData>>
        modifyPassword(@Field("oldPassword") String oldPassword,@Field("newPassword") String newPassword,@Path("session") String session);//修改密码


        @GET("/school/currentweek/schoolCurrentWeek/getWeek;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<CurrentWeekEntity>>
        getWeek(@Path("session") String session);//获取当前周数

        @GET("/school/ctable/schoolCtable/getCtable;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<ScheduleEntity>>>
        getSchedule(@Query("weekNum") String weekNum,@Path("session") String session);//获取当前课表


        @GET("/school/home/schoolHome/getHome;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<HomeMenuEntity>>>
        getHomeMenu(@Path("session") String session);//获取当前课表

        @GET("/school/onclass/schoolOnclass/getLessonOnclass;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<AttendanceEntity>>>
        getAttendance(@Query("weekNum") String weekNum,@Path("session") String session);//获取考勤列表

        @GET("/school/onclass/schoolOnclass/getOnclassInfo;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<AttendanceInfoEntity>>>
        getAttendanceInfo(@Query("weekNum") String weekNum,@Query("lessonId") String lessonId,@Path("session") String session);//获取考勤详情

        @GET("/school/exam/schoolExam/queryCondition;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ConditionResultEntity>>
        getCondition(@Path("session") String session);//获取成绩查询条件

        @FormUrlEncoded
        @POST("/school/exam/schoolExam/queryResults;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<ScoreEntity>>>
        getScore(@Field("subject") String subject,@Field("year") String year,@Field("term") String term,@Field("type") String type,@Field("className") String className,@Path("session") String session);//查询成绩

        @GET("/school/leave/schoolLeave/getLeaveFalse;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<NoteEntity>>>
        getLeaveFalse(@Query("index") int index,@Query("pageSize") int pageSize,@Path("session") String session);//获取未审批假条

        @GET("/school/leave/schoolLeave/getLeaveTrue;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<NoteEntity>>>
        getLeaveTrue(@Query("index") int index,@Query("pageSize") int pageSize,@Path("session") String session);//获取已审批假条

        @FormUrlEncoded
        @POST("/school/leave/schoolLeave/LeaveApproval;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<DefaultData>>
        LeaveApproval(@Field("id") String id,@Field("deal") String deal,@Path("session") String session);//查询成绩

        @GET("/school/homework/schoolHomework/getAllLesson;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<ArrayList<HomeworkEntity>>>
        getHomework(@Query("weekNum") String weekNum,@Path("session") String session);//获取一周的所有课程

        @FormUrlEncoded
        @POST("/school/homework/schoolHomework/saveHomework;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<DefaultData>>
        saveHomeWork(@Field("courseId") String courseId,@Field("content") String content,@Path("session") String session);//查询成绩

        @FormUrlEncoded
        @POST("/school/lessonstudent/schoolLessonStudent/RollCall;JSESSIONID={session}?__ajax=true")
        Observable<AbsReturn<DefaultData>>
        saveRollCall(@Field("week") String week,@Field("lessonid") String lessonid,@Field("rollCallListForm") String rollCallListForm,@Path("session") String session);//查询成绩

    }
}
