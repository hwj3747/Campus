package com.haiyangroup.scampus.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwj3747 on 2016/4/19.
 */
public class ScoreEntity implements Parcelable {
    private	String	studentNo;	/*123123*/
    private	String	name;	/*孙尼玛*/
    private	String	score;	/*88*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.studentNo);
        dest.writeString(this.name);
        dest.writeString(this.score);
    }

    public ScoreEntity() {
    }

    protected ScoreEntity(Parcel in) {
        this.studentNo = in.readString();
        this.name = in.readString();
        this.score = in.readString();
    }

    public static final Creator<ScoreEntity> CREATOR = new Creator<ScoreEntity>() {
        @Override
        public ScoreEntity createFromParcel(Parcel source) {
            return new ScoreEntity(source);
        }

        @Override
        public ScoreEntity[] newArray(int size) {
            return new ScoreEntity[size];
        }
    };
}
