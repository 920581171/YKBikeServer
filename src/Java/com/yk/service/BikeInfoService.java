package com.yk.service;

import com.yk.pojo.BikeInfo;

import java.util.List;

public interface BikeInfoService {
    BikeInfo searchBikeId(String bikeId);
    List<BikeInfo> searchAllBikeInfo();
    int addBikeInfo(String bikeId,double latitude, double longitude);
    int updateBikeInfo(BikeInfo bikeInfo);
    int deleteBikeInfo(BikeInfo bikeInfo);
}
