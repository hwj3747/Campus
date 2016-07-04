package com.haiyangroup.library.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2015/8/20 0020.
 */
public class GetColorKeyword {
    /**
     * 增加需求抽取出需要高亮的文字
     */
    public static String getColorKeyword(String content, String searchStr) {
        if (!TextUtils.isEmpty(content)) {
            int startIndex = (content.toLowerCase()).indexOf(searchStr.toLowerCase());
            StringBuilder sb = new StringBuilder();
            if (startIndex >= 0) {
                if (startIndex > 40) {
                    sb.append("...");
                    sb.append(content.substring(startIndex - 40, startIndex));
                } else {
                    sb.append(content.substring(0, startIndex));
                }
                sb.append("<html><font color='#0090e0'>");
                sb.append(content.substring(startIndex, startIndex + searchStr.length()));
                sb.append("</font></html>");
                sb.append(content.substring(startIndex + searchStr.length(), content.length()));
            } else {
                sb.append(content);
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * 查找字符串的数字
     *
     * @param content
     * @return
     */
    public static String getStringNum(String content) {
        String result = "";
        if (content != null && !"".equals(content)) {
            for (int i = 0; i < content.length(); i++) {
                if (content.charAt(i) >= 48 && content.charAt(i) <= 57) {
                    result += content.charAt(i);
                }
            }
        }
        return result;
    }
}
