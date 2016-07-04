package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author 何伟杰
 * @version 1.0, 2016/6/13
 */
public class ConditionExamListEntity implements Parcelable {
    ArrayList<ConditionExamEntity> term;
    ArrayList<ConditionExamEntity> year;
    ArrayList<ConditionExamEntity> type;

    public ArrayList<ConditionExamEntity> getTerm() {
        return term;
    }

    public void setTerm(ArrayList<ConditionExamEntity> term) {
        this.term = term;
    }

    public ArrayList<ConditionExamEntity> getYear() {
        return year;
    }

    public void setYear(ArrayList<ConditionExamEntity> year) {
        this.year = year;
    }

    public ArrayList<ConditionExamEntity> getType() {
        return type;
    }

    public void setType(ArrayList<ConditionExamEntity> type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.term);
        dest.writeList(this.year);
        dest.writeList(this.type);
    }

    public ConditionExamListEntity() {
    }

    protected ConditionExamListEntity(Parcel in) {
        this.term = new ArrayList<ConditionExamEntity>();
        in.readList(this.term, ConditionExamEntity.class.getClassLoader());
        this.year = new ArrayList<ConditionExamEntity>();
        in.readList(this.year, ConditionExamEntity.class.getClassLoader());
        this.type = new ArrayList<ConditionExamEntity>();
        in.readList(this.type, ConditionExamEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<ConditionExamListEntity> CREATOR = new Parcelable.Creator<ConditionExamListEntity>() {
        @Override
        public ConditionExamListEntity createFromParcel(Parcel source) {
            return new ConditionExamListEntity(source);
        }

        @Override
        public ConditionExamListEntity[] newArray(int size) {
            return new ConditionExamListEntity[size];
        }
    };
}
