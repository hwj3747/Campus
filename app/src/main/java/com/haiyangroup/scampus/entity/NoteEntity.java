package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/19.
 */
public class NoteEntity implements Parcelable {
    private	String	id;	/*65c38273b0f54fcbb06a5508723b3b14*/
    private	String	dealName;	/*不同意*/
    private	Long	start;	/*1465887411000*/
    private	String	reason;	/*不想上课*/
    private	String	name;	/*赵尼玛*/
    private	Long	date;	/*1465887433000*/
    private	String	dealId;	/*0*/
    private	String	photo;	/**/
    private	Long	end;	/*1466146613000*/

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.dealName);
        dest.writeValue(this.start);
        dest.writeString(this.reason);
        dest.writeString(this.name);
        dest.writeValue(this.date);
        dest.writeString(this.dealId);
        dest.writeString(this.photo);
        dest.writeValue(this.end);
    }

    public NoteEntity() {
    }

    protected NoteEntity(Parcel in) {
        this.id = in.readString();
        this.dealName = in.readString();
        this.start = (Long) in.readValue(Long.class.getClassLoader());
        this.reason = in.readString();
        this.name = in.readString();
        this.date = (Long) in.readValue(Long.class.getClassLoader());
        this.dealId = in.readString();
        this.photo = in.readString();
        this.end = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<NoteEntity> CREATOR = new Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel source) {
            return new NoteEntity(source);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };
}
