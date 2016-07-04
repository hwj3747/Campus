package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author 何伟杰
 * @version 1.0, 2016/6/24
 */
public class RollCallListForm implements Parcelable {
    private List<RollCallEntity> rollCallEntities;

    public List<RollCallEntity> getRollCallEntities() {
        return rollCallEntities;
    }

    public void setRollCallEntities(List<RollCallEntity> rollCallEntities) {
        this.rollCallEntities = rollCallEntities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(rollCallEntities);
    }

    public RollCallListForm() {
    }

    protected RollCallListForm(Parcel in) {
        this.rollCallEntities = in.createTypedArrayList(RollCallEntity.CREATOR);
    }

    public static final Parcelable.Creator<RollCallListForm> CREATOR = new Parcelable.Creator<RollCallListForm>() {
        @Override
        public RollCallListForm createFromParcel(Parcel source) {
            return new RollCallListForm(source);
        }

        @Override
        public RollCallListForm[] newArray(int size) {
            return new RollCallListForm[size];
        }
    };
}
