package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/26.
 */
public class MessageEntity implements Parcelable {
    String name;
    String headImg;
    String message;
    String time;
    Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.headImg);
        dest.writeString(this.message);
        dest.writeString(this.time);
        dest.writeValue(this.count);
    }

    public MessageEntity() {
    }

    protected MessageEntity(Parcel in) {
        this.name = in.readString();
        this.headImg = in.readString();
        this.message = in.readString();
        this.time = in.readString();
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<MessageEntity> CREATOR = new Parcelable.Creator<MessageEntity>() {
        @Override
        public MessageEntity createFromParcel(Parcel source) {
            return new MessageEntity(source);
        }

        @Override
        public MessageEntity[] newArray(int size) {
            return new MessageEntity[size];
        }
    };
}
