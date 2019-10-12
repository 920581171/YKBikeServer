package com.yk.bike.dao.impl;

import com.yk.bike.mapper.BalanceRecordMapper;
import com.yk.bike.pojo.BalanceRecord;
import com.yk.bike.dao.BalanceRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.yk.bike.utils.RandomUtils.randomId;

@Service
public class BalanceRecordDaoImpl implements BalanceRecordDao {
    @Autowired
    BalanceRecordMapper balanceRecordMapper;

    @Override
    public BalanceRecord searchBalanceRecordId(String recordId) throws Exception {
        return balanceRecordMapper.selectOne(BalanceRecordMapper.COLUMN_RECORD_ID, recordId);
    }

    @Override
    public List<BalanceRecord> searchBalanceRecordByUserId(String userId) throws Exception {
        return balanceRecordMapper.selectList(BalanceRecordMapper.COLUMN_USER_ID, userId);
    }

    @Override
    public List<BalanceRecord> searchAllBalanceRecord() throws Exception {
        return balanceRecordMapper.selectTable();
    }

    @Override
    public List<BalanceRecord> searchAllDateBalanceRecord(Date startTime, Date endTime) throws Exception {
        return balanceRecordMapper.selectDate(startTime, endTime);
    }

    @Override
    public List<BalanceRecord> queryPageBalanceRecord(int pageIndex, int pageSize) throws Exception {
        return balanceRecordMapper.queryPageTable(pageIndex,pageSize);
    }

    @Override
    public int addBalanceRecord(BalanceRecord balanceRecord) throws Exception {
        String recordId = randomId("BR");
        while (balanceRecordMapper.selectOne(BalanceRecordMapper.COLUMN_RECORD_ID, recordId) != null) {
            recordId = randomId("BR");
        }

        balanceRecord.setRecordId(recordId);
        balanceRecordMapper.insert(balanceRecord);

        return balanceRecord.getId();
    }

    @Override
    public int updateBalanceRecord(BalanceRecord balanceRecord) throws Exception {
        return balanceRecordMapper.update(balanceRecord);
    }

    @Override
    public int deleteBalanceRecord(BalanceRecord balanceRecord) throws Exception {
        return balanceRecordMapper.delete(balanceRecord);
    }
}
