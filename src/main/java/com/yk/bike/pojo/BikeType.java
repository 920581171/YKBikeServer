package com.yk.bike.pojo;

public class BikeType {
    private int id;
    private String typeId;
    private String typeValue;
    private String typeName;
    private float unitPrice;

    public int getId() {
        return id;
    }

    public BikeType setId(int id) {
        this.id = id;
        return this;
    }

    public String getTypeId() {
        return typeId;
    }

    public BikeType setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public BikeType setTypeValue(String typeValue) {
        this.typeValue = typeValue;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public BikeType setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public BikeType setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }
}
