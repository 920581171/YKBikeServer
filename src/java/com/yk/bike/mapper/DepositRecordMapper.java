package com.yk.bike.mapper;

import com.yk.bike.pojo.DepositRecord;

public interface DepositRecordMapper extends BaseMapper<DepositRecord> {
    String COLUMN_RECORD_ID = "record_id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_DEPOSIT = "deposit";
    String COLUMN_CREATE_TIME = "create_time";
}
