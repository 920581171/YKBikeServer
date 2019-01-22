package com.yk.pojo;

import java.sql.Timestamp;

public class BikeRecord {
    private int id;
    private String orderId;
    private String userId;
    private String bikeId;
    private float charge;
    private float mileage;
    private Timestamp createTime;
    private Timestamp endTime;

    public int getId() {
        return id;
    }

    public BikeRecord setId(int id) {
        this.id = id;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public BikeRecord setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public BikeRecord setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getBikeId() {
        return bikeId;
    }

    public BikeRecord setBikeId(String bikeId) {
        this.bikeId = bikeId;
        return this;
    }

    public float getCharge() {
        return charge;
    }

    public BikeRecord setCharge(float charge) {
        this.charge = charge;
        return this;
    }

    public float getMileage() {
        return mileage;
    }

    public BikeRecord setMileage(float mileage) {
        this.mileage = mileage;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public BikeRecord setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public BikeRecord setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }
}
