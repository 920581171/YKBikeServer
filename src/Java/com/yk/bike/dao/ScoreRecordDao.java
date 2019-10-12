package com.yk.bike.dao;

import com.yk.bike.pojo.ScoreRecord;

import java.util.List;

public interface ScoreRecordDao {
    ScoreRecord searchScoreRecordId(String recordId) throws Exception;

    List<ScoreRecord> searchScoreRecordByUserId(String userId) throws Exception;

    List<ScoreRecord> searchAllScoreRecord() throws Exception;

    int addScoreRecord(ScoreRecord scoreRecord) throws Exception;

    int updateScoreRecord(ScoreRecord scoreRecord) throws Exception;

    int deleteScoreRecord(ScoreRecord scoreRecord) throws Exception;
}
