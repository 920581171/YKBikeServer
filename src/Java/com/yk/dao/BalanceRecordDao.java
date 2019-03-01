package com.yk.dao;

import com.yk.pojo.BalanceRecord;
import com.yk.pojo.DepositRecord;

public interface BalanceRecordDao extends BaseDao<BalanceRecord>{
    String COLUMN_RECORD_ID = "record_id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_DEPOSIT = "balance";
    String COLUMN_CREATE_TIME = "create_time";
}
