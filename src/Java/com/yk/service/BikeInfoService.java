package com.yk.service;

import com.yk.pojo.BikeInfo;

import java.util.List;

public interface BikeInfoService {
    BikeInfo searchBikeId(String bikeId) throws Exception;

    List<BikeInfo> searchAllBikeInfo() throws Exception;

    int addBikeInfo(String bikeId, double latitude, double longitude) throws Exception;

    int updateBikeInfo(BikeInfo bikeInfo) throws Exception;

    int deleteBikeInfo(BikeInfo bikeInfo) throws Exception;
}
