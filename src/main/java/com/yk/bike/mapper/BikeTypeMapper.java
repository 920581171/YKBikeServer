package com.yk.bike.mapper;

import com.yk.bike.pojo.BikeType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BikeTypeMapper extends BaseMapper<BikeType> {
    String COLUMN_TYPE_ID = "type_id";
    String COLUMN_TYPE_VALUE = "type_value";
    String COLUMN_TYPE_NAME = "type_name";
    String COLUMN_UNIT_PRICE = "unit_price";
}
