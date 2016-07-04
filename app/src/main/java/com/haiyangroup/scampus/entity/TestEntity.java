package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by hwj3747 on 2016/4/8.
 */
public class TestEntity implements Parcelable {
    String name;
    String pwd;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.pwd);
    }

    public TestEntity() {
    }

    protected TestEntity(Parcel in) {
        this.name = in.readString();
        this.pwd = in.readString();
    }

    public static final Creator<TestEntity> CREATOR = new Creator<TestEntity>() {
        @Override
        public TestEntity createFromParcel(Parcel source) {
            return new TestEntity(source);
        }

        @Override
        public TestEntity[] newArray(int size) {
            return new TestEntity[size];
        }
    };
}
