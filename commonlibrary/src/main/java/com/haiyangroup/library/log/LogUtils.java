/**
 * Copyright (c) 2011, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.haiyangroup.library.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.haiyangroup.library.BuildConfig;
import com.haiyangroup.library.utils.Preferences;
import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.library.utils.common.EnvironmentUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;


@SuppressWarnings("unused")
public class LogUtils {
	// WYJ: Check-in with true will upload logs to UMeng and 
	// disable normal debug loggings
	public static final boolean RELEASE = !BuildConfig.DEBUG; // Do not check-in with true

    /**
     * Filter是否要从服务器下文件
     */
    public static final boolean FILTER_DEBUG = false && !RELEASE;
	/**
	 * 是否开启任务监控窗口
	 * cuij
	 */
	public static final boolean ENABLE_WATCHER = false && !RELEASE;

	public static final boolean DEBUG_USERACTION = false && !RELEASE;

	/**
	 * 这个开关打开后，将会忽略掉sLogStatus里面的参数，也就是忽略掉此Log的Level，而直接使用sCategoryActions里面
	 * 的对应Category的Action进行反应。
	 * 一般情况用在调试代码时，可以忽略掉其它所有的Log，而只输出想要的Category的Log。
	 */
    public static final boolean DEBUG_CATEGORY_ACTIONS = false && !RELEASE;

    public static final boolean DEBUG_MIME = false && !RELEASE;
	public static final boolean DEBUG_IMAP = false && !RELEASE;
	public static final boolean DEBUG_POP = false && !RELEASE;
    public static final boolean DEBUG_EAS = false  && !RELEASE;
	public static final boolean DEBUG_SMTP = false && !RELEASE;
	public static final boolean DEBUG_SERVICE = false && !RELEASE;
	public static final boolean DEBUG_ATTACHMENT = false && !RELEASE;

    public static final boolean DEBUG_QUOTE = false && !RELEASE;
    public static final boolean DEBUG_HTML = false && !RELEASE;

	public static final boolean DEBUG_CONNECTION = false && !RELEASE;
    public static final boolean DEBUG_PERFORMANCE = false && !RELEASE;

    public static final boolean DEBUG_SETUP_FLOWS = false && !RELEASE;
    /**
     * If this is enabled then logging that normally hides sensitive information like passwords will show that information.
     */
    public static final boolean DEBUG_SENSITIVE = false && !RELEASE;

    /**
     * If true, logging regarding UI (such as activity/fragment lifecycle) will be enabled.
     */
    public static final boolean DEBUG_LIFECYCLE = !RELEASE;
    public static final boolean DEBUG_EFFECT = false && !RELEASE;
    public static final boolean DEBUG_TOUCHEVENT = false && !RELEASE;
    public static final boolean DEBUG_CONVERSATION = false && !RELEASE;
    public static final boolean DEBUG_ALARMMANAGER = false && !RELEASE;
    public static final boolean DEBUG_ENTITY = false && !RELEASE;
    public static final boolean DEBUG_READER = false && !RELEASE;
    public static final boolean DEBUG_BYTE_ARRAY_POOL = false & !RELEASE;

    /**
     * Job watcher log switch
     */
    public static final boolean DEBUG_JOB = false && !RELEASE;

    /**
     * flag to enable backend observer log
     */
    public static final boolean DEBUG_CALLBACK = false && !RELEASE;
    /**
     * flag to enable notification log
     */
    public static final boolean DEBUG_NOTIFICATION = false && !RELEASE;

	public static final String LOG_TAG = "JadeMail";
    public static final String LOG_TAG_CONVERSATION = "Conversation";
    public static final String LOG_TAG_DATABASE = "Database";
    public static final String TAG_WEBVIEW = "webview";


    enum LogTarget {
        ADB, FILE, SERVER
    }

    enum LogLevel {
        V, D, I, W, E, /*WT*/F
    }

    private static final int NEVER = 0;
    private static final int DEBUG_ONLY = 1;
    private static final int USER_DEFINED = 2;
    private static final int ALWAYS = 3;

    /**
     * LogCategory和下面的sCategoryActions必须同时修改。为了其他人方便，请将你设置的数组赋值为false
     * @author cuij
     */
    public enum LogCategory
    {
        LIMJ,
        LUORH,
        ZHANGJ,
        MIAOYT,
        HEWJ,
        EFFECT,
        LIFECYCLE,
        CONVERSATION,
        EAS,
        ENTITY,
        QUOTE,
        UNCAUGHT,
        HTTP,
        FILEPREVIEW,
        SENDEMAIL,
        VALIDATE,
        SETUP,
        SETTING,
        WRITER,
        ACTIVITYFRAMEWORK,
        READER,
        ATTACHMENT,
        HOMELIST
    }

    public static final boolean sCategoryActions[][] = {
        /*               ADB        FILE        SERVER  */
        /*LIMJ*/        {false,     false,      false},
        /*LUORH*/       {false,     false,      false},
        /*ZHANGJ*/      {false,     false,      false},
        /*MIAOYT*/      {false,     false,      false},
        /*HEWJ*/        {false,     false,      false},
        /*EFFECT*/      {false,     false,      false},
        /*LIFECYCLE*/   {false,     false,      false},
        /*CONVERSATION*/{false,     false,      false},
        /*EAS*/         {false,     false,      false},
        /*ENTITY*/      {false,     false,      false},
        /*QUOTE*/       {false,     false,      false},
        /*UNCAUGHT*/    {false,     false,      false},
        /*HTTP*/        {false,     false,      false},
        /*FILEPREVIEW*/ {false,     false,      false},
        /*SENDEMAIL*/   {false,     false,      false},
        /*VALIDATE*/    {false,     false,      false},
        /*SETUP*/       {false,     false,      false},
        /*SETTING*/     {false,     false,      false},
        /*WRITER*/      {false,     false,      false},
        /*ACTIVITYFRAMEWORK*/{false,     false,      false},
        /*READER*/      {false,     false,      false},
        /*ATTACHMENT*/  {false,     false,      false},
        /*HOMELIST*/    {false,     false,      false},
    };

    private static final int sLogStatus [][] = {
//                        ADB             FILE            SERVER
        /*VERBOSE*/ {   DEBUG_ONLY,   NEVER,            NEVER},
        /*DEBUG*/   {   DEBUG_ONLY,   NEVER,            NEVER},
        /*INFO*/    {   ALWAYS,       DEBUG_ONLY,       NEVER},
        /*WARN*/    {   ALWAYS,       DEBUG_ONLY,       DEBUG_ONLY},
        /*ERROR*/   {   ALWAYS,       ALWAYS,           DEBUG_ONLY},
        /*WTF*/     {   ALWAYS,       ALWAYS,           ALWAYS},
	};

    // the mock ADB logger must have more information to decide throwables
    private static final HmLogger sLogger [] = {
        new HmLogger() {
            @Override
            public int log(String tag, String str, LogLevel logLevel) {
                // TODO: Write a mock logger to write to ADB
                switch(logLevel) {
                case V:
                    return Log.v(tag, str);
                case D:
                    return Log.d(tag, str);
                case I:
                    return Log.i(tag, str);
                case W:
                    return Log.w(tag, str);
                case E:
                    return Log.e(tag, str);
                case F:
                    return Log.wtf(tag, str);
                }
                return 1;
            }
        },
        null,
        null,
    };

	/**
	 * Constants for MobclickAgent.OnEvent routines
	 * UEID means uMeng Event Id
	 * 事件ID不能使用特殊字符，且长度不能超过50个字符；
	 * “ID”、“ts”、“du”是保留字段，不能做为事件ID；
	 * 事件一旦创建，不能修改事件ID，只能修改事件名称。
	 * 每个应用至多添加500个自定义事件，如果您有更多需求，请联系客服qq: 800083942。
	 */
	public static final String UEID_USER_ACTION = "UserAction";
	public static final String UEID_CLOUD_SERVICE = "CloudService";

	public static final String UEID_ACCOUNT_TYPE_STATISTICS = "AccountTypeStatistics";
	public static final String UEID_ACCOUNT_COUNT_STATISTICS = "AccountCountStatistics";
	/**
	 * UEID_USER_ACTION所带的参数
	 * 每个事件最多可以有10个参数，每个参数最多可以有1000个取值。
	 * TODO: WYJ List all the action parameters
		1 show_all_unread 显示所有未读邮件
		2 setting_from_sliding_menu 从滑动菜单进入设置页面
		3 newer_message 上一封
		4 older_message 下一封
		5 cancel_swipe_dismiss 撤销滑动删除
		6 mark_as_unread_in_messageview 标记为未读
		7 mark_as_star_in_messageview 标记收藏
		8 move_to_folder_in_messageview 移动邮件
		9 move_to_trash_in_messageview 删除邮件
		10 setting_auto_advance 自动跳转
		11 setting_auto_load_picture 自动显示网络图片
		12 setting_push 推送
		13 setting_pull_interval 收邮件时间间隔
		14 setting_auto_download_attachment wifi自动下载附件
		15 setting_auto_sync 只在wifi自动收信
		16 setting_notification 改变通知范围
		17 setting_notification_quiet 静音模式
		18 setting_sync_window 同步天数
		19 setting_sync_folders 同步的文件夹
		20 setting_sync_contact 同步联系人
		21 setting_sync_calendar 同步日历
		22 setting_certificate_request 选择证书
		23 fail_connect_cloud   连接云服务失败
		24 search               搜索
	 */

    // "GMT" + "+" or "-" + 4 digits
    private static final Pattern DATE_CLEANUP_PATTERN_WRONG_TIMEZONE =
            Pattern.compile("GMT([-+]\\d{4})$");

    public static void init(Context context) {
        long curTime = System.currentTimeMillis();
        SimpleDateFormat f = new SimpleDateFormat("yyMMdd_HHmmss");
        sLogger[LogTarget.FILE.ordinal()] = new FileLogger(new File(context.getExternalCacheDir(),
                "FileLog_" + f.format(curTime) + ".txt"));

        // TODO: 把OnlineLogger也改成FileLogger的非单例形式
        sLogger[LogTarget.SERVER.ordinal()]=OnlineLogger.getLogger();
        OnlineLogger.setContext(context);
    }
    
    public static FileLogger getFileLogger() {
        return (FileLogger)sLogger[LogTarget.FILE.ordinal()];
    }

    private static Object[] argsAppend(Object[] args, Object append) {
        Object[] newargs = new Object[args.length + 1];
        System.arraycopy(args, 0, newargs, 0, args.length);
        newargs[args.length] = append;
        return newargs;
    }

    private static int innerLogger(LogLevel logLevel, LogCategory category, String tag,
            Throwable tr, String format, Object... args) {
        String toWrite = null;
        int retVal = 0;

        for (int i = 0; i < LogTarget.values().length; i++) {
            if (isLoggable(logLevel, category, i)) {
                if (toWrite == null) {
                    if (tr != null) {
                        format = format + "\n%s";
                        args = argsAppend(args, Log.getStackTraceString(tr));
                    }
                    toWrite = format(format, args);
                }
                retVal = sLogger[i].log(tag, toWrite, logLevel);
            }
        }
        return retVal;
    }

    private static int innerLogger(LogLevel logLevel, LogCategory category, String tag,
            String format, Object... args) {
        return innerLogger(logLevel, category, tag, null, format, args);
    }

    private static boolean getOutputLogFilePreference() {
        return SharedPreferencesUtil.getInstance(EnvironmentUtils.getAppContext()).getBoolean(Preferences.OUTPUT_LOGFILE,false);
    }

    private static boolean isLoggable(LogLevel logLevel, LogCategory category, int i) {
        if (DEBUG_CATEGORY_ACTIONS && (null != category)) {
            return sCategoryActions[category.ordinal()][i];
        } else {
            int status = sLogStatus[logLevel.ordinal()][i];
            return (status == ALWAYS)
                || (status == DEBUG_ONLY && !RELEASE)
                || (status == USER_DEFINED && getOutputLogFilePreference());
        }
    }

    public static int v(LogCategory category, String format, Object...args) {
        return innerLogger(LogLevel.V, category, category.toString(), format, args);
    }
    
    public static int v(String tag, String format, Object...args) {
        return innerLogger(LogLevel.V, null, tag, format, args);
    }

    public static int d(LogCategory category, String format, Object... args) {
        return innerLogger(LogLevel.D, category, category.toString(), format, args);
    }
    
    public static int d(String tag, String format, Object... args) {
        return innerLogger(LogLevel.D, null, tag, format, args);
    }

    public static int i(LogCategory category, String format, Object... args) {
        return innerLogger(LogLevel.I, category, category.toString(), format, args);
    }
    
    public static int i(String tag, String format, Object... args) {
        return innerLogger(LogLevel.I, null, tag, format, args);
    }

    public static int w(LogCategory category, String format, Object... args) {
        return innerLogger(LogLevel.W, category, category.toString(), format, args);
    }
    
    public static int w(String tag, String format, Object... args) {
        return innerLogger(LogLevel.W, null, tag, format, args);
    }

    public static int e(LogCategory category, String format, Object... args) {
        return innerLogger(LogLevel.E, category, category.toString(), format, args);
    }
    
    public static int e(LogCategory category, Throwable tr, String format, Object... args) {
        return innerLogger(LogLevel.E, category, category.toString(), tr, format, args);
    }
    
    public static int e(String tag, String format, Object... args) {
        return innerLogger(LogLevel.E, null, tag, format, args);
    }
    
    public static int e(String tag, Throwable tr, String format, Object... args) {
        return innerLogger(LogLevel.E, null, tag, tr, format, args);
    }

    public static int wtf(LogCategory category, String format, Object... args) {
        return innerLogger(LogLevel.F, category, category.toString(), format, args);
    }
    
    public static int wtf(LogCategory category, Throwable tr, String format, Object... args) {
        return innerLogger(LogLevel.F, category, category.toString(), tr, format, args);
    }
    
    public static int wtf(String tag, String format, Object... args) {
        return innerLogger(LogLevel.F, null, tag, format, args);
    }
    
    public static int wtf(String tag, Throwable tr, String format, Object... args) {
        return innerLogger(LogLevel.F, null, tag, tr, format, args);
    }

    /**
     * Try to make a date MIME(RFC 2822/5322)-compliant.
     *
     * It fixes:
     * - "Thu, 10 Dec 09 15:08:08 GMT-0700" to "Thu, 10 Dec 09 15:08:08 -0700"
     *   (4 digit zone value can't be preceded by "GMT")
     *   We got a report saying eBay sends a date in this format
     */
    public static String cleanUpMimeDate(String date) {
        if (TextUtils.isEmpty(date)) {
            return date;
        }
        date = DATE_CLEANUP_PATTERN_WRONG_TIMEZONE.matcher(date).replaceFirst("$1");
        return date;
    }

    static String byteToHex(int b) {
        return byteToHex(new StringBuilder(), b).toString();
    }

    static StringBuilder byteToHex(StringBuilder sb, int b) {
        b &= 0xFF;
        sb.append("0123456789ABCDEF".charAt(b >> 4));
        sb.append("0123456789ABCDEF".charAt(b & 0xF));
        return sb;
    }

    /**
     * Call MobclickAgent.onEvent() with LogUtils.UEID_USER_ACTION if RELEASE
     * @param action The first Event param (xxx) passed to LogUtils.ua(getActivity(), xxx);
     */
    public static void ua(Context context, String action)
    {
    	if (RELEASE)
    	{
//    		MobclickAgent.onEvent(context, LogUtils.UEID_USER_ACTION, action);
    	} else {
    	    if(DEBUG_USERACTION){
    	        i(LOG_TAG, LogUtils.UEID_USER_ACTION + ":" + action);
    	    }
    	}
    }

    private static String format(String template, Object... args) {
        if (null == args || 0==args.length)
            return template;

        try {
            return String.format(template, args);
        } catch (Exception e) {
            return "String_format_error";
        }
    }

}
