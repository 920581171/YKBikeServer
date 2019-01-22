package com.yk.impl;

import com.yk.dao.BikeRecordDao;
import com.yk.pojo.BikeRecord;
import com.yk.service.BikeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeRecordServiceImpl implements BikeRecordService {

    @Autowired
    BikeRecordDao bikeRecordDao;


    @Override
    public BikeRecord searchOrderId(String orderId) {
        return bikeRecordDao.selectOne(BikeRecordDao.COLUMN_ORDER_ID,orderId);
    }

    @Override
    public List<BikeRecord> searchUserId(String userId) {
        return bikeRecordDao.selectList(BikeRecordDao.COLUMN_USER_ID,userId);
    }

    @Override
    public List<BikeRecord> searchBikeId(String bikeId) {
        return bikeRecordDao.selectList(BikeRecordDao.COLUMN_BIKE_ID,bikeId);
    }

    @Override
    public List<BikeRecord> searchAllBikeRecord() {
        return bikeRecordDao.selectTable();
    }

    @Override
    public int addBikeRecord(BikeRecord bikeRecord) {
        String orderId = randomOrderId();
        while (bikeRecordDao.selectOne(BikeRecordDao.COLUMN_ORDER_ID, orderId) != null) {
            orderId = randomOrderId();
        }

        bikeRecord.setOrderId(orderId);
        bikeRecordDao.insert(bikeRecord);
        return bikeRecord.getId();
    }

    @Override
    public int updateBikeRecord(BikeRecord bikeRecord) {
        return bikeRecordDao.update(bikeRecord);
    }

    @Override
    public int deleteBikeRecord(BikeRecord bikeRecord) {
        return bikeRecordDao.delete(bikeRecord);
    }

    /**
     * 创建随机userId
     */
    private String randomOrderId() {
        StringBuilder stringBuilder = new StringBuilder("order");
        int random = (int) (Math.random() * 100000);
        stringBuilder.append(random);

        return stringBuilder.toString();
    }
}
