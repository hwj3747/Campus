package com.haiyangroup.library;

import android.os.Environment;

import com.haiyangroup.library.log.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static com.haiyangroup.library.utils.common.EnvironmentUtils.getPackageName;

/**
 * 常量存放区
 */
public abstract class AbsConstant {

    public AbsConstant(){
        init();
    }

    public static String ROOT;

//	public static final String ROOT = "http://192.168.1.156:8080/mpark/";
    /**
     * OFFICIAL : 正式版
     * RC : 预发布
     */
    public static final int OFFICIAL = 0, RC = 1, QA = 2, DEV = 3;

    /**
     * SERVER_TYPE : OFFICIAL、RC、QA、DEV  或 BuildConfig.SERVER_TYPE，<br/>
     * 当为 BuildConfig.SERVER_TYPE 时，由build.gradle自动设置
     */
    protected static int SERVER_TYPE = 0;

    /**
     * 开发联调服务器
     **/
    protected static String DEV_URL_ROOT,QA_URL_ROOT, RC_URL_ROOT,OFFICIAL_URL_ROOT;

    protected abstract String getDev();
    protected abstract String getQa();
    protected abstract String getRc();
    protected abstract String getOffical();

    protected void init(){
        DEV_URL_ROOT = getDev();
        QA_URL_ROOT = getQa();
        RC_URL_ROOT = getRc();
        OFFICIAL_URL_ROOT = getOffical();
        switch (SERVER_TYPE) {
            default:
            case OFFICIAL:
                ROOT = OFFICIAL_URL_ROOT;
                break;
            case RC:
                ROOT = RC_URL_ROOT;
                break;
            case QA:
                ROOT = QA_URL_ROOT;
                break;
            case DEV:
                ROOT = DEV_URL_ROOT;
                break;
        }
    }

    public static String PAGE_COUNT = "page_count";
    public static String LAST_ITEM_ID = "lastItemId";
    public static String PAGE = "page";
    public static int PAGE_SIZE = 10;

    public static final String URL_HTML = "file:///android_asset/html/";

    /**
     * 配置 -- app升级
     */
    public static final String SHARE_AUTO_UPDATE_KEY = "isAutoUpdate";
    /**
     * 配置--- wifi自动下载课件,需要和userId一起使用，对用户区分，显示为userId+":"+SHARE_WIFI_AUTO_DOWNLOAD_COURSE
     **/
    public static final String SHARE_WIFI_AUTO_DOWNLOAD_COURSE = "isAutoDownload";
    //是否开启静默下载
    public static final String SHARE_LP_DOWNLOAD_APPLICATION_ACTIVE = "IS_DOWNLOAD_APPLICATION_ACTIVE";
    /**************************************** 时间常量 ******************************************/
    /**
     * 一秒
     */
    public static long SECOND = 1000L;
    /**
     * 一分
     */
    public static long MINUTE = 60 * SECOND;
    /**
     * 一小时
     */
    public static long HOUR = 60 * MINUTE;
    /**
     * 一天
     */
    public static long DAY = 24 * HOUR;
    /**
     * 一周
     */
    public static long WEEK = DAY * 7;
    /**
     * 半天
     */
    public static long HALF_DAY = 12 * HOUR;
    /**
     * 一月
     */
    public static long MONTH = 30 * DAY;
    /**
     * 一年
     */
    public static long YEAR = 12 * MONTH;

    /**************************************** 加载配置文件 ****************************************/
    static {
        try {
            String path = Environment.getExternalStorageDirectory()
                    + File.separator + getPackageName() + ".properties";
            if ((new File(path).exists())) {
                InputStream inputStream = new FileInputStream(path);
                Properties p = new Properties();
                p.load(inputStream);
                AbsConstant.ROOT = p.getProperty("URL", AbsConstant.ROOT);

                inputStream.close();
                LogUtils.e(LogUtils.LogCategory.HTTP, "College Server: " + AbsConstant.ROOT);
            } else {
                LogUtils.e(LogUtils.LogCategory.HTTP, "Host File Not Found ! ");
            }
        } catch (Exception e) {
            LogUtils.e(LogUtils.LogCategory.HTTP, "Fail in initProperties");
        }
    }

}