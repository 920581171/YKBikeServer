package com.yk.pojo;

public class UserInfo {
    private int id;
    private String userId;
    private String userName;
    private String userPhone;
    private String userPassword;
    private String userSex;

    public int getId() {
        return id;
    }

    public UserInfo setId(int id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public UserInfo setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public UserInfo setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getUserSex() {
        return userSex;
    }

    public UserInfo setUserSex(String userSex) {
        this.userSex = userSex;
        return this;
    }
}
