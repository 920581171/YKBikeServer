package com.yk.service;

import com.yk.pojo.BalanceRecord;

import java.util.List;

public interface BalanceRecordService {
    BalanceRecord searchBalanceRecordId(String recordId) throws Exception;

    List<BalanceRecord> searchBalanceRecordByUserId(String userId) throws Exception;

    List<BalanceRecord> searchAllBalanceRecord() throws Exception;

    List<BalanceRecord> queryPageBalanceRecord(int pageIndex, int pageSize) throws Exception;

    int addBalanceRecord(BalanceRecord balanceRecord) throws Exception;

    int updateBalanceRecord(BalanceRecord balanceRecord) throws Exception;

    int deleteBalanceRecord(BalanceRecord balanceRecord) throws Exception;
}
