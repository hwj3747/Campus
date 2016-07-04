package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 何伟杰
 * @version 1.0, 2016/6/24
 */
public class RollCallEntity implements Parcelable {
    String id;
    Integer rollCall;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getRollCall() {
        return rollCall;
    }
    public void setRollCall(Integer rollCall) {
        this.rollCall = rollCall;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.rollCall);
    }

    public RollCallEntity() {
    }

    protected RollCallEntity(Parcel in) {
        this.id = in.readString();
        this.rollCall = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<RollCallEntity> CREATOR = new Parcelable.Creator<RollCallEntity>() {
        @Override
        public RollCallEntity createFromParcel(Parcel source) {
            return new RollCallEntity(source);
        }

        @Override
        public RollCallEntity[] newArray(int size) {
            return new RollCallEntity[size];
        }
    };
}
