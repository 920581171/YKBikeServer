package com.yk.bike.service;

public interface DepositRecordService {

    String findDepositRecordByUserId(String userId) throws Exception;

    String findAllDateDepositRecord(String startTime, String endTime) throws Exception;

    String findAllDepositRecord() throws Exception;

    String queryPageDepositRecord(int pageIndex, int pageSize) throws Exception;

    String addDepositRecord(String userId, float deposit) throws Exception;

    String updateDepositRecord(String recordId) throws Exception;

    String deleteDepositRecord(String recordId) throws Exception;
}
