package com.yk.bike.service;

public interface BikeInfoService {

    String findAllBikeInfo() throws Exception;

    String queryPageBikeInfo(int pageIndex, int pageSize) throws Exception;

    String findBikeInfoByBikeId(String bikeId) throws Exception;

    String addBikeInfo(String bikeId, String bikeType, double latitude, double longitude) throws Exception;

    String updateBikeInfo(String bikeId, String userId, double latitude, double longitude, float mileage, String fix) throws Exception;

    String updateBikeFix(String bikeId, String fix) throws Exception;

    String updateBikeLocation(String bikeId, double latitude, double longitude) throws Exception;

    String deleteBikeInfo(String bikeId) throws Exception;

    String deleteMoreBikeInfo(String[] bikeIds) throws Exception;
}
