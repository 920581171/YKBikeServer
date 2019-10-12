package com.yk.bike.pojo;

import java.util.Date;

public class ScoreRecord {
    private int id;
    private String recordId;
    private String userId;
    private int score;
    private Date createTime;

    public int getId() {
        return id;
    }

    public ScoreRecord setId(int id) {
        this.id = id;
        return this;
    }

    public String getRecordId() {
        return recordId;
    }

    public ScoreRecord setRecordId(String recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public ScoreRecord setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public int getScore() {
        return score;
    }

    public ScoreRecord setScore(int score) {
        this.score = score;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ScoreRecord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
