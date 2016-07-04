package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 何伟杰
 * @version 1.0, 2016/5/20
 */
public class AgendaContentEntitty implements Parcelable {
    String content;
    String startTime;
    String EndTime;
    String remind;
    String repeat;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.startTime);
        dest.writeString(this.EndTime);
        dest.writeString(this.remind);
        dest.writeString(this.repeat);
    }

    public AgendaContentEntitty() {
    }

    protected AgendaContentEntitty(Parcel in) {
        this.content = in.readString();
        this.startTime = in.readString();
        this.EndTime = in.readString();
        this.remind = in.readString();
        this.repeat = in.readString();
    }

    public static final Parcelable.Creator<AgendaContentEntitty> CREATOR = new Parcelable.Creator<AgendaContentEntitty>() {
        @Override
        public AgendaContentEntitty createFromParcel(Parcel source) {
            return new AgendaContentEntitty(source);
        }

        @Override
        public AgendaContentEntitty[] newArray(int size) {
            return new AgendaContentEntitty[size];
        }
    };
}
