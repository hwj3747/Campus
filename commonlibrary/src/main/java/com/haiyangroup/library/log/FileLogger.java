/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haiyangroup.library.log;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.haiyangroup.library.log.LogUtils.LogLevel;
import com.haiyangroup.library.utils.common.EnvironmentUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class FileLogger implements HmLogger {

    private static final long mFlushGap = TimeUnit.MINUTES.toMillis(1);
    private File mFile;
    private FileOutputStream bw = null;
    private long mLastFlushTime = 0;
    private SimpleDateFormat mTimeFormatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS ");

    public FileLogger(File file) {
        mLastFlushTime = System.currentTimeMillis();
        mFile = file;
        try {
            bw = new FileOutputStream(mFile, true);
            bw.write(getLogHeaderString().getBytes());
        } catch (IOException e) {
            Log.wtf("FileLogger", "Fail to Create and Overwrite LogFile: " + e.toString());
        }
    }

    @Override
    public synchronized int log(String prefix, String str, LogLevel level) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            return -1;

        long curTime = System.currentTimeMillis();

        StringBuffer sb = mTimeFormatter.format(curTime, new StringBuffer(), new FieldPosition(0));
        sb.append("Tid(").append(Thread.currentThread().getId()).append(")|");
        sb.append(level.toString()).append('|').append(prefix);
        sb.append("| ");
        sb.append(str);
        sb.append("\r\n");

        String s = sb.toString();
        if (bw != null) {
            try {
                bw.write(s.getBytes());
                if (level==LogLevel.F || (curTime - mLastFlushTime) > mFlushGap) {
                    bw.flush();
                    bw.getFD().sync();
                    mLastFlushTime = curTime;
                }
            } catch (IOException e) {
                // Something might have happened to the SD card
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    // If the card is mounted and we can create the writer, retry
                    // TODO
                }
            }
        }
        return s.length();
    }
    
    public void saveAndRecreateFile(File newFile, File destFile) {
        if (bw != null) {
            try {
                bw.flush();
                bw.getFD().sync();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (destFile.exists()) {
            destFile.delete();
        }
        if (mFile != null) {
            mFile.renameTo(destFile);
        }
        mLastFlushTime = System.currentTimeMillis();
        mFile = newFile;
        try {
            bw = new FileOutputStream(mFile, true);
            bw.write(getLogHeaderString().getBytes());
        } catch (IOException e) {
            Log.wtf("FileLogger", "Fail to Create and Overwrite LogFile: " + e.toString());
        }
    }

    private static String getLogHeaderString() {
        StringBuilder sb = new StringBuilder("Build Model: ");
        sb.append(Build.MODEL);
        sb.append("\r\nIMEI: ").append(EnvironmentUtils.getTelephonyManager().getDeviceId());
        sb.append("\r\nOS Version: ").append(Build.VERSION.SDK_INT);
        sb.append("\r\nAppVersion: ").append(EnvironmentUtils.getPackageInfo().versionName);
        sb.append("(").append(EnvironmentUtils.getPackageInfo().versionCode);
        sb.append(")\r\nModel: ");
        sb.append(Build.BRAND);
        sb.append(" ");
        sb.append(Build.MODEL);
        sb.append("\r\n\r\n");
        return sb.toString();
    }
}
