package com.yk.bike.mapper;

import com.yk.bike.pojo.BalanceRecord;

public interface BalanceRecordMapper extends BaseMapper<BalanceRecord> {
    String COLUMN_RECORD_ID = "record_id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_DEPOSIT = "balance";
    String COLUMN_CREATE_TIME = "create_time";
}
