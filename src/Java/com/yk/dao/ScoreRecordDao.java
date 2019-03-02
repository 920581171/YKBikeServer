package com.yk.dao;

import com.yk.pojo.ScoreRecord;

public interface ScoreRecordDao extends BaseDao<ScoreRecord>{
    String COLUMN_RECORD_ID = "record_id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_SCORE = "score";
    String COLUMN_CREATE_TIME = "create_time";
}
