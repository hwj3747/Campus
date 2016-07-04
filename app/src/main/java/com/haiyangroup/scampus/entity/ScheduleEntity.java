package com.haiyangroup.scampus.entity;

/**
 * @author 何伟杰
 * @version 1.0, 2016/6/6
 */
public class ScheduleEntity {
    private	String	courseDate;	/*2016-06-06 16:18:04*/
    private	String	remarks;	/**/
    private	String	courseId;	/*c948f673510247bab1af140598128467*/
    private	String	classroomId;	/*东三-401*/
    private	String	updateDate;	/*2016-06-06 16:18:44*/
    private	String	id;	/*9f70e666249f4fbd8b024ecdaf7172b8*/
    private	String	courseOrder;	/*2*/
    private	String	weekday;	/*4*/
    private	String	start;	/*08:55:00*/
    private	String	weekNum;	/*1*/
    private	Boolean	isNewRecord;	/*false*/
    private	String	createDate;	/*2016-06-06 16:18:44*/
    private	String	teacherId;	/*吴老师*/

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseOrder() {
        return courseOrder;
    }

    public void setCourseOrder(String courseOrder) {
        this.courseOrder = courseOrder;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }
}
