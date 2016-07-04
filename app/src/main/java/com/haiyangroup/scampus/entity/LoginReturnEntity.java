package com.haiyangroup.scampus.entity;

/**
 * @author 何伟杰
 * @version 1.0, 2016/5/31
 */
public class LoginReturnEntity {
    private	String	id;	/*1*/
    private	String	name;	/*系统管理员*/
    private	String	sessionid;	/*b6b486a8919e4fc196358e10b6a82a2b*/
    private	Boolean	mobileLogin;	/*true*/
    private	String	loginName;	/*system*/
    private String  message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(String value){
        this.id = value;
    }
    public String getId(){
        return this.id;
    }

    public void setName(String value){
        this.name = value;
    }
    public String getName(){
        return this.name;
    }

    public void setSessionid(String value){
        this.sessionid = value;
    }
    public String getSessionid(){
        return this.sessionid;
    }

    public void setMobileLogin(Boolean value){
        this.mobileLogin = value;
    }
    public Boolean getMobileLogin(){
        return this.mobileLogin;
    }

    public void setLoginName(String value){
        this.loginName = value;
    }
    public String getLoginName(){
        return this.loginName;
    }
}
