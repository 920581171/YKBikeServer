package com.yk.bike.mapper;

import com.yk.bike.pojo.BikeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BikeRecordMapper extends BaseMapper<BikeRecord> {
    String COLUMN_ORDER_ID="order_id";
    String COLUMN_USER_ID="user_id";
    String COLUMN_BIKE_ID="bike_id";
    String COLUMN_COUNT="charge";
    String COLUMN_MILEAGE="mileage";
    String COLUMN_CREATE_TIME="create_time";
    String COLUMN_END_TIME="end_time";

    BikeRecord selectCycling(@Param("userId") String userId);
}
