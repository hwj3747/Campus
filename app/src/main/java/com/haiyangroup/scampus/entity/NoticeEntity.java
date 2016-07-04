package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/18.
 */
public class NoticeEntity implements Parcelable {
    private	String	content;	/*4*/
    private	String	id;	/*a82ee2059d574189a8df790f7669272f*/
    private	String	title;	/*4*/
    private	String	noticeId;	/*4*/
    private	String	address;	/*4*/
    private	Boolean	isNewRecord;	/*false*/
    private	String	remarks;	/*4*/
    private	String	createDate;	/*2016-05-30 10:56:07*/
    private	String	updateDate;	/*2016-05-30 10:56:07*/

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

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
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
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.noticeId);
        dest.writeString(this.address);
        dest.writeValue(this.isNewRecord);
        dest.writeString(this.remarks);
        dest.writeString(this.createDate);
        dest.writeString(this.updateDate);
    }

    public NoticeEntity() {
    }

    protected NoticeEntity(Parcel in) {
        this.content = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.noticeId = in.readString();
        this.address = in.readString();
        this.isNewRecord = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.remarks = in.readString();
        this.createDate = in.readString();
        this.updateDate = in.readString();
    }

    public static final Creator<NoticeEntity> CREATOR = new Creator<NoticeEntity>() {
        @Override
        public NoticeEntity createFromParcel(Parcel source) {
            return new NoticeEntity(source);
        }

        @Override
        public NoticeEntity[] newArray(int size) {
            return new NoticeEntity[size];
        }
    };
}
