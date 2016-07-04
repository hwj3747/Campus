package com.haiyangroup.scampus.data;

/**
 * 服务端默认返回数据模板
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public class AbsReturn<V> {
    String message;
    V data;
    int  code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
