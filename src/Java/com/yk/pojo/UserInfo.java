package com.yk.pojo;

public class UserInfo {
    private int id;
    private String userId;
    private String userName;
    private String userPhone;
    private String userPassword;
    private float deposit;
    private float balance;
    private int score;

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

    public float getDeposit() {
        return deposit;
    }

    public UserInfo setDeposit(float deposit) {
        this.deposit = deposit;
        return this;
    }

    public float getBalance() {
        return balance;
    }

    public UserInfo setBalance(float balance) {
        this.balance = balance;
        return this;
    }

    public int getScore() {
        return score;
    }

    public UserInfo setScore(int score) {
        this.score = score;
        return this;
    }
}
