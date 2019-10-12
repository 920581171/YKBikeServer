package com.yk.bike.service;

public interface ScoreRecordService {
    String findScoreRecordByUserId(String userId) throws Exception;

    String addScoreRecord(String userId, int score) throws Exception;

    String updateScoreRecord(String recordId) throws Exception;

    String deleteBalanceRecord(String recordId) throws Exception;
}
