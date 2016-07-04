package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/25.
 */
public class ContactsChildEntity implements Parcelable {
    String headImg;
    String name;
    String message;
    String phone;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.headImg);
        dest.writeString(this.name);
        dest.writeString(this.message);
        dest.writeString(this.phone);
    }

    public ContactsChildEntity() {
    }

    protected ContactsChildEntity(Parcel in) {
        this.headImg = in.readString();
        this.name = in.readString();
        this.message = in.readString();
        this.phone = in.readString();
    }

    public static final Parcelable.Creator<ContactsChildEntity> CREATOR = new Parcelable.Creator<ContactsChildEntity>() {
        @Override
        public ContactsChildEntity createFromParcel(Parcel source) {
            return new ContactsChildEntity(source);
        }

        @Override
        public ContactsChildEntity[] newArray(int size) {
            return new ContactsChildEntity[size];
        }
    };
}
