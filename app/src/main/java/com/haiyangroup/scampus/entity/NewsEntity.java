package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/16.
 */
public class NewsEntity implements Parcelable {
    private	String	content;	/*5*/
    private	String	newId;	/*5*/
    private	String	id;	/*c1c95ea0ccf9494b91e9c8e5db961f46*/
    private	String	cover;	/**/
    private	String	title;	/*5*/
    private	String	address;	/*5*/
    private	Boolean	isNewRecord;	/*false*/
    private	String	remarks;	/*5*/
    private	String	createDate;	/*2016-05-30 10:47:17*/
    private	String	updateDate;	/*2016-05-30 10:47:17*/


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(Boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.newId);
        dest.writeString(this.id);
        dest.writeString(this.cover);
        dest.writeString(this.title);
        dest.writeString(this.address);
        dest.writeValue(this.isNewRecord);
        dest.writeString(this.remarks);
        dest.writeString(this.createDate);
        dest.writeString(this.updateDate);
    }

    public NewsEntity() {
    }

    protected NewsEntity(Parcel in) {
        this.content = in.readString();
        this.newId = in.readString();
        this.id = in.readString();
        this.cover = in.readString();
        this.title = in.readString();
        this.address = in.readString();
        this.isNewRecord = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.remarks = in.readString();
        this.createDate = in.readString();
        this.updateDate = in.readString();
    }

    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel source) {
            return new NewsEntity(source);
        }

        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };
}
