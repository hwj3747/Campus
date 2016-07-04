package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/22.
 */
public class HomeworkEntity implements Parcelable {
    private	String	id;	/*c36ca2acf30a4e2c8dfd8b4e1e3f4c58*/
    private	String	lesson;	/*大学英语*/
    private	String	time;	/*06-08*/
    private	String	start;	/*14:55:00*/
    private	String	end;	/*15:40:00*/
    private	String	week;	/*3*/

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
        dest.writeString(this.start);
        dest.writeString(this.end);
        dest.writeString(this.week);
    }

    public HomeworkEntity() {
    }

    protected HomeworkEntity(Parcel in) {
        this.id = in.readString();
        this.lesson = in.readString();
        this.time = in.readString();
        this.start = in.readString();
        this.end = in.readString();
        this.week = in.readString();
    }

    public static final Creator<HomeworkEntity> CREATOR = new Creator<HomeworkEntity>() {
        @Override
        public HomeworkEntity createFromParcel(Parcel source) {
            return new HomeworkEntity(source);
        }

        @Override
        public HomeworkEntity[] newArray(int size) {
            return new HomeworkEntity[size];
        }
    };
}
