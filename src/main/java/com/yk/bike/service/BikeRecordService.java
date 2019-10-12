package com.yk.bike.service;

public interface BikeRecordService {
    String findBikeRecordIsCycling(String userId) throws Exception;

    String findBikeRecordByOrderId(String orderId) throws Exception;

    String findBikeRecordByUserId(String userId) throws Exception;

    String findBikeRecordByBikeId(String bikeId) throws Exception;

    String findAllBikeRecord() throws Exception;

    String findAllDateBikeRecord(String startTime, String endTime) throws Exception;

    String queryPageBikeRecord(int pageIndex, int pageSize) throws Exception;

    String addBikeRecord(String userId, String bikeId, String bikeType) throws Exception;

    String updateBikeRecord(String orderId, String userId, String bikeId, String bikeType, float charge, float mileage, long endTime, String orderStatus) throws Exception;

    String finishBike(String orderId, double latitude, double longitude) throws Exception;

    String deleteBikeRecord(String orderId) throws Exception;

}
