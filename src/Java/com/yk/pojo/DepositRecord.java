package com.yk.pojo;

import java.util.Date;

public class DepositRecord {
    private int id;
    private String recordId;
    private String userId;
    private float deposit;
    private Date createTime;

    public int getId() {
        return id;
    }

    public DepositRecord setId(int id) {
        this.id = id;
        return this;
    }

    public String getRecordId() {
        return recordId;
    }

    public DepositRecord setRecordId(String recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public DepositRecord setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public float getDeposit() {
        return deposit;
    }

    public DepositRecord setDeposit(float deposit) {
        this.deposit = deposit;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DepositRecord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
