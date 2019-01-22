package com.yk.impl;

import com.yk.dao.BikeInfoDao;
import com.yk.pojo.BikeInfo;
import com.yk.service.BikeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeInfoServiceImpl implements BikeInfoService {
    @Autowired
    private BikeInfoDao bikeInfoDao;

    @Override
    public BikeInfo searchBikeId(String bikeId) {
        return bikeInfoDao.selectOne(BikeInfoDao.COLUMN_BIKE_ID, bikeId);
    }

    @Override
    public List<BikeInfo> searchAllBikeInfo() {
        return bikeInfoDao.selectTable();
    }

    @Override
    public int addBikeInfo(String bikeId, double latitude, double longitude) {

        BikeInfo bikeInfo = searchBikeId(bikeId);

        if (searchBikeId(bikeId) != null) {
            bikeInfo.setLatitude(latitude);
            bikeInfo.setLongitude(longitude);
            bikeInfoDao.update(bikeInfo);
        } else {
            bikeInfo = new BikeInfo()
                    .setBikeId(bikeId)
                    .setLatitude(latitude)
                    .setLongitude(longitude)
                    .setMileage(0)
                    .setFix("0");
            bikeInfoDao.insert(bikeInfo);
        }
        return bikeInfo.getId();
    }

    @Override
    public int updateBikeInfo(BikeInfo bikeInfo) {
        return bikeInfoDao.update(bikeInfo);
    }

    @Override
    public int deleteBikeInfo(BikeInfo bikeInfo) {
        return bikeInfoDao.delete(bikeInfo);
    }
}
