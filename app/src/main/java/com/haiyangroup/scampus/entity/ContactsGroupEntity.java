package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/25.
 */
public class ContactsGroupEntity implements Parcelable {
    String groupName;
    String number;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.groupName);
        dest.writeString(this.number);
    }

    public ContactsGroupEntity() {
    }

    protected ContactsGroupEntity(Parcel in) {
        this.groupName = in.readString();
        this.number = in.readString();
    }

    public static final Parcelable.Creator<ContactsGroupEntity> CREATOR = new Parcelable.Creator<ContactsGroupEntity>() {
        @Override
        public ContactsGroupEntity createFromParcel(Parcel source) {
            return new ContactsGroupEntity(source);
        }

        @Override
        public ContactsGroupEntity[] newArray(int size) {
            return new ContactsGroupEntity[size];
        }
    };
}
