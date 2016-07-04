package com.haiyangroup.library.log;

import com.haiyangroup.library.log.LogUtils.LogLevel;

public interface HmLogger {
    int log(String tag, String str, LogLevel logLevel);
}
