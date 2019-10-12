package com.yk.bike.dao;

import com.yk.bike.pojo.DepositRecord;

import java.util.Date;
import java.util.List;

public interface DepositRecordDao {
    DepositRecord searchDepositRecordId(String recordId) throws Exception;

    List<DepositRecord> searchDepositRecordByUserId(String userId) throws Exception;

    List<DepositRecord> searchAllDepositRecord() throws Exception;

    List<DepositRecord> searchAllDateDepositRecord(Date startTime, Date endTime) throws Exception;

    List<DepositRecord> queryPageDepositRecord(int pageIndex,int pageSize) throws Exception;

    int addDepositRecord(DepositRecord depositRecord) throws Exception;

    int updateDepositRecord(DepositRecord depositRecord) throws Exception;

    int deleteDepositRecord(DepositRecord depositRecord) throws Exception;
}
