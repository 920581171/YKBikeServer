package com.yk.dao;

import com.yk.pojo.BikeInfo;

public interface BikeInfoDao extends BaseDao<BikeInfo>{
    String COLUMN_USER_ID="user_id";
    String COLUMN_BIKE_ID="bike_id";
    String COLUMN_LATITUDE="latitude";
    String COLUMN_LONGITUDE="longitude";
    String COLUMN_MILEAGE="mileage";
}
