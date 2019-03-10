package com.yk.impl;

import com.yk.dao.ChatMessageDao;
import com.yk.dao.DepositRecordDao;
import com.yk.pojo.DepositRecord;
import com.yk.service.DepositRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.yk.Utils.RandomUtils.randomId;

@Service
public class DepositRecordServiceImpl implements DepositRecordService {
    @Autowired
    DepositRecordDao depositRecordDao;

    @Override
    public DepositRecord searchDepositRecordId(String recordId) throws Exception {
        return depositRecordDao.selectOne(DepositRecordDao.COLUMN_RECORD_ID, recordId);
    }

    @Override
    public List<DepositRecord> searchDepositRecordByUserId(String userId) throws Exception {
        return depositRecordDao.selectList(DepositRecordDao.COLUMN_USER_ID, userId);
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
        while (depositRecordDao.selectOne(DepositRecordDao.COLUMN_RECORD_ID, recordId) != null) {
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
