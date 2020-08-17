package com.yk.bike.mapper;

import com.yk.bike.pojo.BikeInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BikeInfoMapper extends BaseMapper<BikeInfo> {
    String COLUMN_USER_ID="user_id";
    String COLUMN_BIKE_ID="bike_id";
    String COLUMN_LATITUDE="latitude";
    String COLUMN_LONGITUDE="longitude";
    String COLUMN_MILEAGE="mileage";
}
