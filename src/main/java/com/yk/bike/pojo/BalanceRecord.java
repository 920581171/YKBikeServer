package com.yk.bike.pojo;

import java.util.Date;

public class BalanceRecord {
    private int id;
    private String recordId;
    private String userId;
    private float balance;
    private Date createTime;
    private String isExchange;

    public int getId() {
        return id;
    }

    public BalanceRecord setId(int id) {
        this.id = id;
        return this;
    }

    public String getRecordId() {
        return recordId;
    }

    public BalanceRecord setRecordId(String recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public BalanceRecord setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public float getBalance() {
        return balance;
    }

    public BalanceRecord setBalance(float balance) {
        this.balance = balance;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BalanceRecord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getIsExchange() {
        return isExchange;
    }

    public BalanceRecord setIsExchange(String isExchange) {
        this.isExchange = isExchange;
        return this;
    }
}
