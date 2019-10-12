package com.yk.bike.dao;

import com.yk.bike.pojo.BalanceRecord;

import java.util.Date;
import java.util.List;

public interface BalanceRecordDao {
    BalanceRecord searchBalanceRecordId(String recordId) throws Exception;

    List<BalanceRecord> searchBalanceRecordByUserId(String userId) throws Exception;

    List<BalanceRecord> searchAllBalanceRecord() throws Exception;

    List<BalanceRecord> searchAllDateBalanceRecord(Date startTime, Date endTime) throws Exception;

    List<BalanceRecord> queryPageBalanceRecord(int pageIndex, int pageSize) throws Exception;

    int addBalanceRecord(BalanceRecord balanceRecord) throws Exception;

    int updateBalanceRecord(BalanceRecord balanceRecord) throws Exception;

    int deleteBalanceRecord(BalanceRecord balanceRecord) throws Exception;
}
