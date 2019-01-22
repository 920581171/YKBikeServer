package com.yk.service;

import com.yk.pojo.BikeRecord;

import java.util.List;

public interface BikeRecordService {
    BikeRecord searchOrderId(String orderId);
    List<BikeRecord> searchUserId(String userId);
    List<BikeRecord> searchBikeId(String bikeId);
    List<BikeRecord> searchAllBikeRecord();
    int addBikeRecord(BikeRecord bikeRecord);
    int updateBikeRecord(BikeRecord bikeRecord);
    int deleteBikeRecord(BikeRecord bikeRecord);
}
