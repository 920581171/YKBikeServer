package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.BalanceRecordDaoImpl;
import com.yk.bike.dao.impl.ScoreRecordDaoImpl;
import com.yk.bike.dao.impl.UserInfoDaoImpl;
import com.yk.bike.pojo.BalanceRecord;
import com.yk.bike.pojo.ScoreRecord;
import com.yk.bike.pojo.UserInfo;
import com.yk.bike.service.ScoreRecordService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScoreRecordServiceImpl implements ScoreRecordService {

    @Autowired
    private ScoreRecordDaoImpl scoreRecordDao;
    @Autowired
    BalanceRecordDaoImpl balanceRecordDao;
    @Autowired
    UserInfoDaoImpl userInfoDao;

    @Override
    public String findScoreRecordByUserId(String userId) throws Exception {
        List<ScoreRecord> scoreRecords = scoreRecordDao.searchScoreRecordByUserId(userId);
        return GsonUtils.responseObjectJson(scoreRecords != null, scoreRecords);
    }

    @Override
    public String addScoreRecord(String userId, int score) throws Exception {
        UserInfo userInfo = userInfoDao.searchUserId(userId);
        if (userInfo == null) {
            return GsonUtils.responseErrorJson();
        }

        float balance = (float) (Math.round(score / 10f * 100) / 100);
        userInfo.setBalance(userInfo.getBalance() + balance)
                .setScore(userInfo.getScore() - score);
        boolean b = userInfoDao.updateUserInfo(userInfo) > 0;

        if (!b) {
            return GsonUtils.responseErrorJson();
        }

        Date createTime = new Date(System.currentTimeMillis());

        BalanceRecord balanceRecord = new BalanceRecord().setUserId(userId)
                .setBalance(balance)
                .setCreateTime(createTime)
                .setIsExchange("1");

        balanceRecordDao.addBalanceRecord(balanceRecord);

        ScoreRecord scoreRecord = new ScoreRecord().setUserId(userId)
                .setScore(-score)
                .setCreateTime(createTime);

        return GsonUtils.responseSimpleJson(scoreRecordDao.addScoreRecord(scoreRecord) > 0);
    }

    @Override
    public String updateScoreRecord(String recordId) throws Exception {
        ScoreRecord scoreRecord = scoreRecordDao.searchScoreRecordId(recordId);
        return GsonUtils.responseObjectJson(scoreRecordDao.updateScoreRecord(scoreRecord) > 0, scoreRecord);
    }

    @Override
    public String deleteBalanceRecord(String recordId) throws Exception {
        return GsonUtils.responseSimpleJson(scoreRecordDao.deleteScoreRecord(new ScoreRecord().setRecordId(recordId)) > 0);
    }
}
