package com.yk.service;

import com.yk.pojo.BikeRecord;

import java.util.Date;
import java.util.List;

public interface BikeRecordService {
    BikeRecord searchCycling(String userId) throws Exception;

    BikeRecord searchById(String id) throws Exception;

    BikeRecord searchOrderId(String orderId) throws Exception;

    List<BikeRecord> searchUserId(String userId) throws Exception;

    List<BikeRecord> searchBikeId(String bikeId) throws Exception;

    List<BikeRecord> searchAllBikeRecord() throws Exception;

    List<BikeRecord> searchAllDate(Date startTime,Date endTime) throws Exception;

    List<BikeRecord> queryPageBikeRecord(int pageIndex,int pageSize) throws Exception;

    int addBikeRecord(BikeRecord bikeRecord) throws Exception;

    int updateBikeRecord(BikeRecord bikeRecord) throws Exception;

    int deleteBikeRecord(BikeRecord bikeRecord) throws Exception;
}
