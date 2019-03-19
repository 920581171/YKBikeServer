package com.yk.pojo;

import java.util.Date;

public class BikeRecord {
    private int id;
    private String orderId;
    private String userId;
    private String bikeId;
    private String bikeType;
    private float charge;
    private float mileage;
    private Date createTime;
    private Date endTime;
    private String orderStatus;

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

    public String getBikeType() {
        return bikeType;
    }

    public BikeRecord setBikeType(String bikeType) {
        this.bikeType = bikeType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public BikeRecord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public BikeRecord setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public BikeRecord setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }
}
