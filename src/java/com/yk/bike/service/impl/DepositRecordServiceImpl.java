package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.DepositRecordDaoImpl;
import com.yk.bike.dao.impl.UserInfoDaoImpl;
import com.yk.bike.pojo.DepositRecord;
import com.yk.bike.pojo.UserInfo;
import com.yk.bike.service.DepositRecordService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DepositRecordServiceImpl implements DepositRecordService {

    @Autowired
    private DepositRecordDaoImpl depositRecordDao;
    @Autowired
    private UserInfoDaoImpl userInfoDao;

    @Override
    public String findDepositRecordByUserId(String userId) throws Exception {
        List<DepositRecord> depositRecords = depositRecordDao.searchDepositRecordByUserId(userId);
        return GsonUtils.responseObjectJson(depositRecords != null, depositRecords);
    }

    @Override
    public String findAllDateDepositRecord(String startTime, String endTime) throws Exception {
        DateFormat format = SimpleDateFormat.getDateInstance();
        List<DepositRecord> depositRecords = depositRecordDao.searchAllDateDepositRecord(format.parse(startTime), format.parse(endTime));
        return GsonUtils.responseObjectJson(depositRecords != null, depositRecords);
    }

    @Override
    public String findAllDepositRecord() throws Exception {
        List<DepositRecord> depositRecords = depositRecordDao.searchAllDepositRecord();
        return GsonUtils.responseObjectJson(depositRecords != null, depositRecords);
    }

    @Override
    public String queryPageDepositRecord(int pageIndex, int pageSize) throws Exception {
        List<DepositRecord> depositRecords = depositRecordDao.queryPageDepositRecord(pageIndex, pageSize);
        return GsonUtils.responseObjectJson(depositRecords != null, depositRecords);
    }

    @Override
    public String addDepositRecord(String userId, float deposit) throws Exception {
        UserInfo userInfo = userInfoDao.searchUserId(userId);
        if (userInfo == null) {
            return GsonUtils.responseErrorJson();
        }

        userInfo.setDeposit(deposit);
        boolean b = userInfoDao.updateUserInfo(userInfo) > 0;

        if (!b) {
            return GsonUtils.responseErrorJson();
        }

        if (deposit == 0) {
            deposit = -199;
        }
        DepositRecord depositRecord = new DepositRecord().setUserId(userId)
                .setDeposit(deposit)
                .setCreateTime(new Date(System.currentTimeMillis()));

        return GsonUtils.responseSimpleJson(depositRecordDao.addDepositRecord(depositRecord) > 0);
    }

    @Override
    public String updateDepositRecord(String recordId) throws Exception {
        DepositRecord depositRecord = depositRecordDao.searchDepositRecordId(recordId);
        return GsonUtils.responseObjectJson(depositRecordDao.updateDepositRecord(depositRecord) > 0, depositRecord);
    }

    @Override
    public String deleteDepositRecord(String recordId) throws Exception {
        return GsonUtils.responseSimpleJson(depositRecordDao.deleteDepositRecord(new DepositRecord().setRecordId(recordId)) > 0);
    }
}
