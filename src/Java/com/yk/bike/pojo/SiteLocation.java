package com.yk.bike.pojo;

public class SiteLocation {
    private int id;
    private String siteId;
    private double latitude;
    private double longitude;
    private int radius;

    public int getId() {
        return id;
    }

    public SiteLocation setId(int id) {
        this.id = id;
        return this;
    }

    public String getSiteId() {
        return siteId;
    }

    public SiteLocation setSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public SiteLocation setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public SiteLocation setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public int getRadius() {
        return radius;
    }

    public SiteLocation setRadius(int radius) {
        this.radius = radius;
        return this;
    }
}
