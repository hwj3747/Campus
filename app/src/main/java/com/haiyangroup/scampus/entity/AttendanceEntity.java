package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/21.
 */
public class AttendanceEntity implements Parcelable {
    private	String	id;	/*55124ac3cf87444384c38a3a12c1137b*/
    private	String	lesson;	/*高等数学*/
    private	String	time;	/*05-27*/
    private	Integer	count;	/*1*/
    private	String	start;	/*08:00:00*/
    private	String	end;	/*08:45:00*/
    private	String	week;	/*5*/

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.lesson);
        dest.writeString(this.time);
        dest.writeValue(this.count);
        dest.writeString(this.start);
        dest.writeString(this.end);
        dest.writeString(this.week);
    }

    public AttendanceEntity() {
    }

    protected AttendanceEntity(Parcel in) {
        this.id = in.readString();
        this.lesson = in.readString();
        this.time = in.readString();
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.start = in.readString();
        this.end = in.readString();
        this.week = in.readString();
    }

    public static final Creator<AttendanceEntity> CREATOR = new Creator<AttendanceEntity>() {
        @Override
        public AttendanceEntity createFromParcel(Parcel source) {
            return new AttendanceEntity(source);
        }

        @Override
        public AttendanceEntity[] newArray(int size) {
            return new AttendanceEntity[size];
        }
    };
}
