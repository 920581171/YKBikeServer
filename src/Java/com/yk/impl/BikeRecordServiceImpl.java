package com.yk.impl;

import com.yk.dao.BikeRecordDao;
import com.yk.pojo.BikeRecord;
import com.yk.service.BikeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.Utils.RandomUtils.randomId;

@Service
public class BikeRecordServiceImpl implements BikeRecordService {

    @Autowired
    BikeRecordDao bikeRecordDao;

    @Override
    public BikeRecord searchCycling(String userId) throws Exception {
        return bikeRecordDao.selectCycling(userId);
    }

    @Override
    public BikeRecord searchById(String id) throws Exception {
        return bikeRecordDao.selectOne(BikeRecordDao.COLUMN_ID, id);
    }

    @Override
    public BikeRecord searchOrderId(String orderId) throws Exception {
        return bikeRecordDao.selectOne(BikeRecordDao.COLUMN_ORDER_ID, orderId);
    }

    @Override
    public List<BikeRecord> searchUserId(String userId) throws Exception {
        return bikeRecordDao.selectList(BikeRecordDao.COLUMN_USER_ID, userId);
    }

    @Override
    public List<BikeRecord> searchBikeId(String bikeId) throws Exception {
        return bikeRecordDao.selectList(BikeRecordDao.COLUMN_BIKE_ID, bikeId);
    }

    @Override
    public List<BikeRecord> searchAllBikeRecord() throws Exception {
        return bikeRecordDao.selectTable();
    }

    @Override
    public int addBikeRecord(BikeRecord bikeRecord) throws Exception {
        String orderId = randomId("RECORD");
        while (bikeRecordDao.selectOne(BikeRecordDao.COLUMN_ORDER_ID, orderId) != null) {
            orderId = randomId("RECORD");
        }

        bikeRecord.setOrderId(orderId);
        bikeRecordDao.insert(bikeRecord);
        return bikeRecord.getId();
    }

    @Override
    public int updateBikeRecord(BikeRecord bikeRecord) throws Exception {
        return bikeRecordDao.update(bikeRecord);
    }

    @Override
    public int deleteBikeRecord(BikeRecord bikeRecord) throws Exception {
        return bikeRecordDao.delete(bikeRecord);
    }
}
