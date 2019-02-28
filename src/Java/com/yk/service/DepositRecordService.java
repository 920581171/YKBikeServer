package com.yk.service;

import com.yk.pojo.DepositRecord;

import java.util.List;

public interface DepositRecordService{
    DepositRecord searchDepositRecordId(String recordId) throws Exception;

    List<DepositRecord> searchDepositRecordByUserId(String userId) throws Exception;

    List<DepositRecord> searchAllDepositRecord() throws Exception;

    int addDepositRecord(DepositRecord depositRecord) throws Exception;

    int updateDepositRecord(DepositRecord depositRecord) throws Exception;

    int deleteDepositRecord(DepositRecord depositRecord) throws Exception;
}
