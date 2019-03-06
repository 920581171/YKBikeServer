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
    public BikeInfo searchBikeId(String bikeId) throws Exception {
        return bikeInfoDao.selectOne(BikeInfoDao.COLUMN_BIKE_ID, bikeId);
    }

    @Override
    public List<BikeInfo> searchAllBikeInfo() throws Exception {
        return bikeInfoDao.selectTable();
    }

    @Override
    public List<BikeInfo> queryPageBikeInfo(int pageIndex, int pageSize) throws Exception {
        return bikeInfoDao.queryPageTable(pageIndex,pageSize);
    }

    @Override
    public int addBikeInfo(String bikeId, double latitude, double longitude) throws Exception {

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
    public int updateBikeInfo(BikeInfo bikeInfo) throws Exception {
        return bikeInfoDao.update(bikeInfo);
    }

    @Override
    public int deleteBikeInfo(BikeInfo bikeInfo) throws Exception {
        return bikeInfoDao.delete(bikeInfo);
    }
}
