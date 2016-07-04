package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/21.
 */
public class AttendanceInfoEntity implements Parcelable {
    private	String	studentNo;	/*123123*/
    private	String	reason;	/*没问题*/
    private	String	name;	/*孙尼玛*/
    private	String	className;	/*16级一班*/

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AttendanceInfoEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.studentNo);
        dest.writeString(this.reason);
        dest.writeString(this.name);
        dest.writeString(this.className);
    }

    protected AttendanceInfoEntity(Parcel in) {
        this.studentNo = in.readString();
        this.reason = in.readString();
        this.name = in.readString();
        this.className = in.readString();
    }

    public static final Creator<AttendanceInfoEntity> CREATOR = new Creator<AttendanceInfoEntity>() {
        @Override
        public AttendanceInfoEntity createFromParcel(Parcel source) {
            return new AttendanceInfoEntity(source);
        }

        @Override
        public AttendanceInfoEntity[] newArray(int size) {
            return new AttendanceInfoEntity[size];
        }
    };
}
