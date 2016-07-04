package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/21.
 */
public class CalendarEntity implements Parcelable {
    private	String	picture;	/*www.baidu.com*/
    private	String	id;	/*1*/
    private	String	title;	/*开学*/
    private	String	start;	/*2016-03-01 00:00:00*/
    private	Boolean	isNewRecord;	/*false*/
    private	String	remarks;	/**/
    private	String	createDate;	/*2016-05-13 14:41:19*/
    private	String	end;	/*2016-05-31 00:00:00*/
    private	String	updateDate;	/*2016-05-31 11:41:50*/

    public void setPicture(String value){
        this.picture = value;
    }
    public String getPicture(){
        return this.picture;
    }

    public void setId(String value){
        this.id = value;
    }
    public String getId(){
        return this.id;
    }

    public void setTitle(String value){
        this.title = value;
    }
    public String getTitle(){
        return this.title;
    }

    public void setStart(String value){
        this.start = value;
    }
    public String getStart(){
        return this.start;
    }

    public void setIsNewRecord(Boolean value){
        this.isNewRecord = value;
    }
    public Boolean getIsNewRecord(){
        return this.isNewRecord;
    }

    public void setRemarks(String value){
        this.remarks = value;
    }
    public String getRemarks(){
        return this.remarks;
    }

    public void setCreateDate(String value){
        this.createDate = value;
    }
    public String getCreateDate(){
        return this.createDate;
    }

    public void setEnd(String value){
        this.end = value;
    }
    public String getEnd(){
        return this.end;
    }

    public void setUpdateDate(String value){
        this.updateDate = value;
    }
    public String getUpdateDate(){
        return this.updateDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.picture);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.start);
        dest.writeValue(this.isNewRecord);
        dest.writeString(this.remarks);
        dest.writeString(this.createDate);
        dest.writeString(this.end);
        dest.writeString(this.updateDate);
    }

    public CalendarEntity() {
    }

    protected CalendarEntity(Parcel in) {
        this.picture = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.start = in.readString();
        this.isNewRecord = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.remarks = in.readString();
        this.createDate = in.readString();
        this.end = in.readString();
        this.updateDate = in.readString();
    }

    public static final Creator<CalendarEntity> CREATOR = new Creator<CalendarEntity>() {
        @Override
        public CalendarEntity createFromParcel(Parcel source) {
            return new CalendarEntity(source);
        }

        @Override
        public CalendarEntity[] newArray(int size) {
            return new CalendarEntity[size];
        }
    };
}
