package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.*;
import com.yk.bike.pojo.*;
import com.yk.bike.service.BikeRecordService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class BikeRecordServiceImpl implements BikeRecordService {

    @Autowired
    private BikeRecordDaoImpl bikeRecordDao;

    @Autowired
    private BikeInfoDaoImpl bikeInfoDao;

    @Autowired
    private UserInfoDaoImpl userInfoDao;
    @Autowired
    private BalanceRecordDaoImpl balanceRecordDao;
    @Autowired
    private ScoreRecordDaoImpl scoreRecordDao;
    @Autowired
    private BikeTypeDaoImpl bikeTypeDao;

    @Override
    public String findBikeRecordIsCycling(String userId) throws Exception {
        BikeRecord bikeRecord = bikeRecordDao.searchCycling(userId);
        return GsonUtils.responseObjectJson(bikeRecord != null, bikeRecord);
    }

    @Override
    public String findBikeRecordByOrderId(String orderId) throws Exception {
        BikeRecord bikeRecord = bikeRecordDao.searchOrderId(orderId);
        return GsonUtils.responseObjectJson(bikeRecord != null, bikeRecord);
    }

    @Override
    public String findBikeRecordByUserId(String userId) throws Exception {
        List<BikeRecord> bikeRecords = bikeRecordDao.searchUserId(userId);
        return GsonUtils.responseObjectJson(bikeRecords != null, bikeRecords);
    }

    @Override
    public String findBikeRecordByBikeId(String bikeId) throws Exception {
        List<BikeRecord> bikeRecords = bikeRecordDao.searchBikeId(bikeId);
        return GsonUtils.responseObjectJson(bikeRecords != null, bikeRecords);
    }

    @Override
    public String findAllBikeRecord() throws Exception {
        List<BikeRecord> bikeRecords = bikeRecordDao.searchAllBikeRecord();
        return GsonUtils.responseObjectJson(bikeRecords != null, bikeRecords);
    }

    @Override
    public String findAllDateBikeRecord(String startTime, String endTime) throws Exception {
        DateFormat format = SimpleDateFormat.getDateInstance();
        List<BikeRecord> bikeRecords = bikeRecordDao.searchAllDate(format.parse(startTime), format.parse(endTime));
        return GsonUtils.responseObjectJson(bikeRecords != null, bikeRecords);
    }

    @Override
    public String queryPageBikeRecord(int pageIndex, int pageSize) throws Exception {
        List<BikeRecord> bikeRecords = bikeRecordDao.queryPageBikeRecord(pageIndex, pageSize);
        return GsonUtils.responseObjectJson(bikeRecords != null, bikeRecords);
    }

    @Override
    public String addBikeRecord(String userId, String bikeId, String bikeType) throws Exception {
        BikeInfo bikeInfo = bikeInfoDao.searchBikeId(bikeId);
        if (bikeInfoDao.updateBikeInfo(bikeInfo.setUserId(userId)) > 0) {
            BikeRecord bikeRecord = new BikeRecord()
                    .setUserId(userId)
                    .setBikeId(bikeId)
                    .setBikeType(bikeType)
                    .setCharge(0f)
                    .setMileage(0f)
                    .setCreateTime(new Date(System.currentTimeMillis()))
                    .setEndTime(new Date(System.currentTimeMillis()))
                    .setOrderStatus("0");
            return GsonUtils.responseObjectJson(bikeRecordDao.addBikeRecord(bikeRecord) > 0, bikeRecordDao.searchById(String.valueOf(bikeRecord.getId())));

        } else {
            return GsonUtils.responseErrorJson();
        }
    }

    @Override
    public String updateBikeRecord(String orderId, String userId, String bikeId, String bikeType, float charge, float mileage, long endTime, String orderStatus) throws Exception {
        BikeRecord bikeRecord = bikeRecordDao.searchOrderId(orderId);
        bikeRecord.setOrderId(orderId)
                .setUserId(userId)
                .setBikeId(bikeId)
                .setBikeId(bikeType)
                .setCharge(charge)
                .setMileage(mileage)
                .setEndTime(new Date(endTime))
                .setOrderStatus(orderStatus);

        return GsonUtils.responseSimpleJson(bikeRecordDao.updateBikeRecord(bikeRecord) > 0);
    }

    @Override
    public String finishBike(String orderId, double latitude, double longitude) throws Exception {
        LinkedHashMap<String, Float> unitPrice = bikeTypeDao.getMapBikeTypeUnitPrice();
        BikeRecord bikeRecord = bikeRecordDao.searchOrderId(orderId);
        BikeInfo bikeInfo = bikeInfoDao.searchBikeId(bikeRecord.getBikeId());
        UserInfo userInfo = userInfoDao.searchUserId(bikeRecord.getUserId());
        if (bikeInfoDao.updateBikeInfo(bikeInfo.setUserId("").setLatitude(latitude).setLongitude(longitude)) > 0) {
            long createTime = bikeRecord.getCreateTime().getTime();
            long endTime = System.currentTimeMillis();

            float charge = (endTime - createTime) / 1000f / 60f / 10f;
            charge = charge * unitPrice.get(bikeInfo.getBikeType());
            charge = (float) (Math.round(charge * 100)) / 100;

            bikeRecord.setOrderId(orderId)
                    .setCharge(charge)
                    .setMileage(0f)
                    .setEndTime(new Date(endTime))
                    .setOrderStatus("1");

            if (bikeRecordDao.updateBikeRecord(bikeRecord) > 0) {
                Date systemTime = new Date(System.currentTimeMillis());
                float balance = userInfo.getBalance() - charge;
                balance = (float) (Math.round(balance * 100)) / 100;
                int score = (int) (charge * 100);

                boolean b = userInfoDao.updateUserInfo(userInfo.setBalance(balance).setScore(userInfo.getScore() + score)) > 0;
                if (b && balance < 0) {
                    return GsonUtils.responseErrorMsgJson("余额不足");
                }

                BalanceRecord balanceRecord = new BalanceRecord().setUserId(userInfo.getUserId())
                        .setBalance(-charge)
                        .setCreateTime(systemTime)
                        .setIsExchange("0");

                boolean b2 = balanceRecordDao.addBalanceRecord(balanceRecord) > 0;

                ScoreRecord scoreRecord = new ScoreRecord().setUserId(userInfo.getUserId())
                        .setScore(score)
                        .setCreateTime(systemTime);

                boolean b3 = scoreRecordDao.addScoreRecord(scoreRecord) > 0;

                return GsonUtils.responseObjectJson(b && b2 && b3, bikeRecordDao.searchOrderId(orderId));
            } else {
                return GsonUtils.responseErrorJson();
            }
        } else {
            return GsonUtils.responseErrorJson();
        }
    }

    @Override
    public String deleteBikeRecord(String orderId) throws Exception {
        BikeRecord bikeRecord = bikeRecordDao.searchOrderId(orderId);
        return GsonUtils.responseSimpleJson(bikeRecordDao.deleteBikeRecord(bikeRecord) > 0);
    }
}
