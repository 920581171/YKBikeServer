package com.yk.dao;

import com.yk.pojo.BikeType;

public interface BikeTypeDao extends BaseDao<BikeType>{
    String COLUMN_TYPE_ID = "type_id";
    String COLUMN_TYPE_VALUE = "type_value";
    String COLUMN_TYPE_NAME = "type_name";
    String COLUMN_UNIT_PRICE = "unit_price";
}
