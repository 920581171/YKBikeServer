package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.BalanceRecordDaoImpl;
import com.yk.bike.dao.impl.UserInfoDaoImpl;
import com.yk.bike.pojo.BalanceRecord;
import com.yk.bike.pojo.UserInfo;
import com.yk.bike.service.BalanceRecordService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BalanceRecordServiceImpl implements BalanceRecordService {

    @Autowired
    private BalanceRecordDaoImpl balanceRecordDao;
    @Autowired
    private UserInfoDaoImpl userInfoDao;

    @Override
    public String findBalanceRecordByUserId(String userId) throws Exception {
        List<BalanceRecord> balanceRecords = balanceRecordDao.searchBalanceRecordByUserId(userId);
        return GsonUtils.responseObjectJson(balanceRecords != null, balanceRecords);
    }

    @Override
    public String findAllBalanceRecord() throws Exception {
        List<BalanceRecord> balanceRecords = balanceRecordDao.searchAllBalanceRecord();
        return GsonUtils.responseObjectJson(balanceRecords != null, balanceRecords);
    }

    @Override
    public String findAllDateBalanceRecord(String startTime, String endTime) throws Exception {
        DateFormat format = SimpleDateFormat.getDateInstance();
        List<BalanceRecord> balanceRecords = balanceRecordDao.searchAllDateBalanceRecord(format.parse(startTime), format.parse(endTime));
        return GsonUtils.responseObjectJson(balanceRecords != null, balanceRecords);
    }

    @Override
    public String queryPageBalanceRecord(int pageIndex, int pageSize) throws Exception {
        List<BalanceRecord> balanceRecords = balanceRecordDao.queryPageBalanceRecord(pageIndex, pageSize);
        return GsonUtils.responseObjectJson(balanceRecords != null, balanceRecords);
    }

    @Override
    public String addBalanceRecord(String userId, float balance) throws Exception {
        UserInfo userInfo = userInfoDao.searchUserId(userId);
        if (userInfo == null) {
            return GsonUtils.responseErrorJson();
        }

        userInfo.setBalance(userInfo.getBalance() + balance);
        boolean b = userInfoDao.updateUserInfo(userInfo) > 0;

        if (!b) {
            return GsonUtils.responseErrorJson();
        }

        BalanceRecord balanceRecord = new BalanceRecord().setUserId(userId)
                .setBalance(balance)
                .setCreateTime(new Date(System.currentTimeMillis()))
                .setIsExchange("0");

        return GsonUtils.responseSimpleJson(balanceRecordDao.addBalanceRecord(balanceRecord) > 0);
    }

    @Override
    public String updateBalanceRecord(String recordId) throws Exception {
        BalanceRecord balanceRecord = balanceRecordDao.searchBalanceRecordId(recordId);
        return GsonUtils.responseObjectJson(balanceRecordDao.updateBalanceRecord(balanceRecord) > 0, balanceRecord);
    }

    @Override
    public String deleteBalanceRecord(String recordId) throws Exception {
        return GsonUtils.responseSimpleJson(balanceRecordDao.deleteBalanceRecord(new BalanceRecord().setRecordId(recordId)) > 0);
    }
}
