package com.yk.dao;

import com.yk.pojo.DepositRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepositRecordDao extends BaseDao<DepositRecord>{
    String COLUMN_RECORD_ID = "record_id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_DEPOSIT = "deposit";
    String COLUMN_DEPOSIT_STATUS = "deposit_status";
    String COLUMN_CREATE_TIME = "create_time";
    String COLUMN_UPDATE_TIME = "update_time";
}
