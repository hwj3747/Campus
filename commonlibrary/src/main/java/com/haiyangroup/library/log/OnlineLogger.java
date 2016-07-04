package com.haiyangroup.library.log;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.haiyangroup.library.log.LogUtils.LogLevel;
import com.haiyangroup.library.utils.common.ConnectivityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipOutputStream;

public class OnlineLogger implements HmLogger {
    private static OnlineLogger LOGGER = null;
    private BufferedOutputStream bw = null;
    private long mLastFlushTime = 0;
    private long mUploadDelayTime = 0;
    
    private final int mBufferSize = 1024;
    private final long mFlushGap = TimeUnit.HOURS.toMillis(2);
    private final String cacheFile = "cacheLog.txt";
    private final String zipName = "zippedLog";
    private final long mUploadGap = TimeUnit.DAYS.toMillis(7);
    private final int mFileSize = 1024 * 1024;
    
    private static Context mContext;
    
    private static String Model;
    private static final int JADEMESG_SIGNATURE = 0x4AADE900;
    private static final int JADEMESG_VERSION = 0x00000001;
    private static final int UPLOAD = 1;
    private static final int ERROR_SUCCESS = 0;
    //some device may can't get deviceId;
    private static File zipFile;
    public synchronized static OnlineLogger getLogger () {
        if (LOGGER == null) {
            LOGGER = new OnlineLogger();
        }
        return LOGGER;
    }
    
  //everytime setup, upload after a random time plus 10 minutes, delay time (10, 110) mins;    
    private OnlineLogger() {
        Model = Build.MODEL;
        mLastFlushTime =  new Date().getTime();
        Random random = new Random();
        mUploadDelayTime =mLastFlushTime + (10 + random.nextInt()%100) * TimeUnit.MINUTES.toMillis(1);
    }
    
    private void init() {
        try {
            bw =  new BufferedOutputStream(mContext.openFileOutput(cacheFile, Context.MODE_APPEND), mBufferSize);
            zipFile = new File(mContext.getFilesDir() + "/" + zipName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated constructor stub
    }
    
    public synchronized void close() {
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException e) {
                // Doesn't matter
            }
            bw = null;
        }
    }

    public synchronized int log(String prefix, String str) {
        if (LOGGER == null) {
            LOGGER = new OnlineLogger();
            LOGGER.init();
            log("Logger", "\r\n\r\n --- New Log ---");
        }
        Date d = new Date();
        
        if ( ((d.getTime() - mLastFlushTime) > mFlushGap) ) {
            try {
                bw.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mLastFlushTime = d.getTime();
        }
        
        DateFormat f = SimpleDateFormat.getInstance();
        String dataStr = f.format(d);

        // I don't use DateFormat here because (in my experience), it's much slower
        StringBuffer sb = new StringBuffer(256);
        sb.append(dataStr + " ");
        if (prefix != null) {
            sb.append(prefix);
            sb.append("| ");
        }
        sb.append(str);
        sb.append("\r\n");
        String s = sb.toString();

        if (bw != null) {
            try {
                bw.write(s.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } 
        
        if (condition()) {
            Thread thread = new Thread() {
                public void run() {
                    upload();
                }
            };
            thread.run();
        }
        return 0;
    }

    @Override
    public synchronized int log(String prefix, String str, LogLevel logLevel) {
//        String Tag = logLevel.toString() + "|" + prefix;
//        return log(Tag, str);
        return -1;
    }
    
    //delete log that has been uploaded;
    private synchronized void reInit() {
        try {
            bw.close();
            bw =  new BufferedOutputStream(mContext.openFileOutput(cacheFile, Context.MODE_PRIVATE), mBufferSize);
            zipFile.delete();
        }   catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
    
    private synchronized void zipFile() {
        try {
            BufferedInputStream bins = new BufferedInputStream(mContext.openFileInput(cacheFile));
            ZipOutputStream zouts = new ZipOutputStream(mContext.openFileOutput(zipName, Context.MODE_PRIVATE));
            byte buffer[] = new byte[mBufferSize];
            while (bins.read(buffer) > 0) {
                zouts.write(buffer);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            
        }
    }
    
    private synchronized boolean realUpload() {
        Log.w("OnlineLogger", "UploadMechanism");
//        
//        JadeMesgHeader header = new JadeMesgHeader(JADEMESG_SIGNATURE|JADEMESG_VERSION, IMEI, UPLOAD);
//        JadeLog logBody = new JadeLog(new Date().getTime(), 
//                Model, 
//                (int)zipFile.length(),
//                0);
//        header.setExternalDataSize(logBody.getSize() + logBody.getFileSize());
//        try {
//            Socket socket = SSLtoServer.getSSLSocket(mContext);
//            OutputStream ous = socket.getOutputStream();
//            ous.write(header.getBytes());
//            ous.write(logBody.getBytes());
//            byte [] buffer = new byte[mBufferSize];
//            BufferedInputStream ins = new BufferedInputStream(new FileInputStream(upFile), mBufferSize);
//            while (ins.read(buffer) > 0) {
//                ous.write(buffer);
//            }
//            InputStream netIns = socket.getInputStream();
//            netIns.read(buffer);
//            if (JadeMesgHeader.getReply(buffer) == UP_OK) {
//                return true;
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return false;
    }
    
    //set it as a upload task, needn't do it immediately;
    private synchronized void upload() {
        zipFile();
        if(realUpload()){
            reInit();
        }
    }
    
    /**
     * 
     * @return
     * determine whether to upload
     */
    public boolean condition() {
        if (new Date().getTime() > mUploadDelayTime && ConnectivityUtils.isNonMobileConnected()) {
            if (zipFile.length() > mFileSize || (new Date().getTime() - zipFile.lastModified() > mUploadGap)) {
                
                return true;
            }
        }
        return false;
    }
    
    public static void setContext(Context context) {
        mContext = context;
        LOGGER.init();
    }
}
