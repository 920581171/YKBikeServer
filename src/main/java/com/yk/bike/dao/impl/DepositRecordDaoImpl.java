package com.yk.bike.dao.impl;

import com.yk.bike.mapper.DepositRecordMapper;
import com.yk.bike.pojo.DepositRecord;
import com.yk.bike.dao.DepositRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.yk.bike.utils.RandomUtils.randomId;

@Service
public class DepositRecordDaoImpl implements DepositRecordDao {
    @Autowired
    DepositRecordMapper depositRecordDao;

    @Override
    public DepositRecord searchDepositRecordId(String recordId) throws Exception {
        return depositRecordDao.selectOne(DepositRecordMapper.COLUMN_RECORD_ID, recordId);
    }

    @Override
    public List<DepositRecord> searchDepositRecordByUserId(String userId) throws Exception {
        return depositRecordDao.selectList(DepositRecordMapper.COLUMN_USER_ID, userId);
    }

    @Override
    public List<DepositRecord> searchAllDepositRecord() throws Exception {
        return depositRecordDao.selectTable();
    }

    @Override
    public List<DepositRecord> searchAllDateDepositRecord(Date startTime, Date endTime) throws Exception {
        return depositRecordDao.selectDate(startTime,endTime);
    }

    @Override
    public List<DepositRecord> queryPageDepositRecord(int pageIndex, int pageSize) throws Exception {
        return depositRecordDao.queryPageTable(pageIndex,pageSize);
    }

    @Override
    public int addDepositRecord(DepositRecord depositRecord) throws Exception {
        String recordId = randomId("DR");
        while (depositRecordDao.selectOne(DepositRecordMapper.COLUMN_RECORD_ID, recordId) != null) {
            recordId = randomId("DR");
        }

        depositRecord.setRecordId(recordId);
        depositRecordDao.insert(depositRecord);

        return depositRecord.getId();
    }

    @Override
    public int updateDepositRecord(DepositRecord depositRecord) throws Exception {
        return depositRecordDao.update(depositRecord);
    }

    @Override
    public int deleteDepositRecord(DepositRecord depositRecord) throws Exception {
        return depositRecordDao.delete(depositRecord);
    }
}
