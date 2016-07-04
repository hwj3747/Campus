package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 何伟杰
 * @version 1.0, 2016/6/13
 */
public class ConditionExamEntity implements Parcelable {
    String id;
    String label;
    String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.label);
        dest.writeString(this.value);
    }

    public ConditionExamEntity() {
    }

    protected ConditionExamEntity(Parcel in) {
        this.id = in.readString();
        this.label = in.readString();
        this.value = in.readString();
    }

    public static final Creator<ConditionExamEntity> CREATOR = new Creator<ConditionExamEntity>() {
        @Override
        public ConditionExamEntity createFromParcel(Parcel source) {
            return new ConditionExamEntity(source);
        }

        @Override
        public ConditionExamEntity[] newArray(int size) {
            return new ConditionExamEntity[size];
        }
    };
}
