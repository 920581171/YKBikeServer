package com.yk.bike.service;

public interface BalanceRecordService {

    String findBalanceRecordByUserId(String userId) throws Exception;

    String findAllBalanceRecord() throws Exception;

    String findAllDateBalanceRecord(String startTime, String endTime) throws Exception;

    String queryPageBalanceRecord(int pageIndex, int pageSize) throws Exception;

    String addBalanceRecord(String userId, float balance) throws Exception;

    String updateBalanceRecord(String recordId) throws Exception;

    String deleteBalanceRecord(String recordId) throws Exception;
}
