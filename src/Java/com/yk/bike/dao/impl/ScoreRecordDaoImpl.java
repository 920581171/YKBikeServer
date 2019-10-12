package com.yk.bike.dao.impl;

import com.yk.bike.mapper.ScoreRecordMapper;
import com.yk.bike.pojo.ScoreRecord;
import com.yk.bike.dao.ScoreRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.bike.utils.RandomUtils.randomId;

@Service
public class ScoreRecordDaoImpl implements ScoreRecordDao {
    @Autowired
    ScoreRecordMapper scoreRecordDao;

    @Override
    public ScoreRecord searchScoreRecordId(String recordId) throws Exception {
        return scoreRecordDao.selectOne(ScoreRecordMapper.COLUMN_RECORD_ID, recordId);
    }

    @Override
    public List<ScoreRecord> searchScoreRecordByUserId(String userId) throws Exception {
        List<ScoreRecord> scoreRecords = scoreRecordDao.selectList(ScoreRecordMapper.COLUMN_USER_ID, userId);
        System.out.println(scoreRecords.get(0).getRecordId());
        return scoreRecordDao.selectList(ScoreRecordMapper.COLUMN_USER_ID, userId);
    }

    @Override
    public List<ScoreRecord> searchAllScoreRecord() throws Exception {
        return scoreRecordDao.selectTable();
    }

    @Override
    public int addScoreRecord(ScoreRecord scoreRecord) throws Exception {
        String recordId = randomId("SR");
        while (scoreRecordDao.selectOne(ScoreRecordMapper.COLUMN_RECORD_ID, recordId) != null) {
            recordId = randomId("SR");
        }

        scoreRecord.setRecordId(recordId);
        scoreRecordDao.insert(scoreRecord);

        return scoreRecord.getId();
    }

    @Override
    public int updateScoreRecord(ScoreRecord scoreRecord) throws Exception {
        return scoreRecordDao.update(scoreRecord);
    }

    @Override
    public int deleteScoreRecord(ScoreRecord scoreRecord) throws Exception {
        return scoreRecordDao.delete(scoreRecord);
    }
}
