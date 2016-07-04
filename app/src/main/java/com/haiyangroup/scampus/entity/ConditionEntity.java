package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 何伟杰
 * @version 1.0, 2016/6/13
 */
public class ConditionEntity implements Parcelable {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    public ConditionEntity() {
    }

    protected ConditionEntity(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ConditionEntity> CREATOR = new Parcelable.Creator<ConditionEntity>() {
        @Override
        public ConditionEntity createFromParcel(Parcel source) {
            return new ConditionEntity(source);
        }

        @Override
        public ConditionEntity[] newArray(int size) {
            return new ConditionEntity[size];
        }
    };
}
