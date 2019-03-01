package com.yk.impl;

import com.yk.dao.BalanceRecordDao;
import com.yk.pojo.BalanceRecord;
import com.yk.service.BalanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.Utils.RandomUtils.randomId;

@Service
public class BalanceRecordServiceImpl implements BalanceRecordService {
    @Autowired
    BalanceRecordDao balanceRecordDao;

    @Override
    public BalanceRecord searchBalanceRecordId(String recordId) throws Exception {
        return balanceRecordDao.selectOne(BalanceRecordDao.COLUMN_RECORD_ID, recordId);
    }

    @Override
    public List<BalanceRecord> searchBalanceRecordByUserId(String userId) throws Exception {
        return balanceRecordDao.selectList(BalanceRecordDao.COLUMN_USER_ID, userId);
    }

    @Override
    public List<BalanceRecord> searchAllBalanceRecord() throws Exception {
        return balanceRecordDao.selectTable();
    }

    @Override
    public int addBalanceRecord(BalanceRecord balanceRecord) throws Exception {
        String recordId = randomId("BR");
        while (balanceRecordDao.selectOne(BalanceRecordDao.COLUMN_RECORD_ID, recordId) != null) {
            recordId = randomId("BR");
        }

        balanceRecord.setRecordId(recordId);
        balanceRecordDao.insert(balanceRecord);

        return balanceRecord.getId();
    }

    @Override
    public int updateBalanceRecord(BalanceRecord balanceRecord) throws Exception {
        return balanceRecordDao.update(balanceRecord);
    }

    @Override
    public int deleteBalanceRecord(BalanceRecord balanceRecord) throws Exception {
        return balanceRecordDao.delete(balanceRecord);
    }
}
