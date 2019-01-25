package com.yk.service;

import com.yk.pojo.BikeRecord;

import java.util.List;

public interface BikeRecordService {
    BikeRecord searchOrderId(String orderId) throws Exception;

    List<BikeRecord> searchUserId(String userId) throws Exception;

    List<BikeRecord> searchBikeId(String bikeId) throws Exception;

    List<BikeRecord> searchAllBikeRecord() throws Exception;

    int addBikeRecord(BikeRecord bikeRecord) throws Exception;

    int updateBikeRecord(BikeRecord bikeRecord) throws Exception;

    int deleteBikeRecord(BikeRecord bikeRecord) throws Exception;
}
