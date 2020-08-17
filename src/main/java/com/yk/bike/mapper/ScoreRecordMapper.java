package com.yk.bike.mapper;

import com.yk.bike.pojo.ScoreRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreRecordMapper extends BaseMapper<ScoreRecord> {
    String COLUMN_RECORD_ID = "record_id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_SCORE = "score";
    String COLUMN_CREATE_TIME = "create_time";
}
