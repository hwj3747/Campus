package com.haiyangroup.scampus.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Json转换的工具类
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class GsonUtil {


    /**
     * 将json字符串转化为Java类<br>
     * @param json，json字符串<br>‘
     * @param clazz，要转化的类T<br>
     * @return T
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        T t = gson.fromJson(json, clazz);
        return t;
    }


    /**
     * 将Java对象转化为json字符串<br>
     * @param object，要转化的对象<br>‘
     * @return String
     */
    public static String toJson(Object object) {
        Gson gson = new Gson();
        String t = gson.toJson(object);
        return t;
    }


    /**
     * 将json字符串转化为map<br>
     * @param json，要转化的json字符串<br>‘
     * @return Map<String, JsonElement>
     */
    public static Map<String, JsonElement> fromJson2MapJson(String json) {
        Gson gson = new Gson();
        Map<String, JsonElement> map = gson.fromJson(json,
                new TypeToken<Map<String, JsonElement>>() {
                }.getType());
        return map;
    }

    /**
     * 将json字符串转化为对象数组<br>
     * @param json，要转化的json字符串<br>‘
     * @param clazz，转化的对象类型<br>‘
     * @return ArrayList<T>
     */
    public static <T> ArrayList<T> fromJson2List(String json, Class<T> clazz) {
        Gson gson = new Gson();
        ArrayList<JsonObject> list = null;
        list = gson.fromJson(json, new TypeToken<List<JsonObject>>() {
        }.getType());
        ArrayList<T> resultList = new ArrayList<T>();
        for (JsonObject t : list) {
            resultList.add(new Gson().fromJson(t, clazz));
        }
        return resultList;
    }


}
