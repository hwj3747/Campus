package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author 何伟杰
 * @version 1.0, 2016/6/13
 */
public class ConditionResultEntity implements Parcelable {
    ArrayList<ConditionEntity> subject;
    ArrayList<ConditionEntity> className;
    ConditionExamListEntity exam;

    public ArrayList<ConditionEntity> getClassName() {
        return className;
    }

    public void setClassName(ArrayList<ConditionEntity> className) {
        this.className = className;
    }

    public ConditionExamListEntity getExam() {
        return exam;
    }

    public void setExam(ConditionExamListEntity exam) {
        this.exam = exam;
    }

    public ArrayList<ConditionEntity> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<ConditionEntity> subject) {
        this.subject = subject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.subject);
        dest.writeList(this.className);
        dest.writeParcelable(this.exam, flags);
    }

    public ConditionResultEntity() {
    }

    protected ConditionResultEntity(Parcel in) {
        this.subject = new ArrayList<ConditionEntity>();
        in.readList(this.subject, ConditionEntity.class.getClassLoader());
        this.className = new ArrayList<ConditionEntity>();
        in.readList(this.className, ConditionEntity.class.getClassLoader());
        this.exam = in.readParcelable(ConditionExamListEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<ConditionResultEntity> CREATOR = new Parcelable.Creator<ConditionResultEntity>() {
        @Override
        public ConditionResultEntity createFromParcel(Parcel source) {
            return new ConditionResultEntity(source);
        }

        @Override
        public ConditionResultEntity[] newArray(int size) {
            return new ConditionResultEntity[size];
        }
    };
}
