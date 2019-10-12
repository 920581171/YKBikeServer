package com.yk.bike.service.impl;

import com.yk.bike.dao.BikeInfoDao;
import com.yk.bike.pojo.BikeInfo;
import com.yk.bike.service.BikeInfoService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeInfoServiceImpl implements BikeInfoService {

    @Autowired
    private BikeInfoDao bikeInfoDao;

    @Override
    public String findAllBikeInfo() throws Exception {
        List<BikeInfo> bikeInfos = bikeInfoDao.searchAllBikeInfo();
        return GsonUtils.responseObjectJson(bikeInfos != null, bikeInfos);
    }

    @Override
    public String queryPageBikeInfo(int pageIndex, int pageSize) throws Exception {
        List<BikeInfo> bikeInfos = bikeInfoDao.queryPageBikeInfo(pageIndex, pageSize);
        return GsonUtils.responseObjectJson(bikeInfos != null, bikeInfos);
    }

    @Override
    public String findBikeInfoByBikeId(String bikeId) throws Exception {
        BikeInfo bikeInfo = bikeInfoDao.searchBikeId(bikeId);
        return GsonUtils.responseObjectJson(bikeInfo != null, bikeInfo);
    }

    @Override
    public String addBikeInfo(String bikeId, String bikeType, double latitude, double longitude) throws Exception {
        return GsonUtils.responseSimpleJson(bikeInfoDao.addBikeInfo(bikeId, bikeType, latitude, longitude) > 0);
    }

    @Override
    public String updateBikeInfo(String bikeId, String userId, double latitude, double longitude, float mileage, String fix) throws Exception {
        BikeInfo bikeInfo = bikeInfoDao.searchBikeId(bikeId);
        bikeInfo.setUserId(userId)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setMileage(mileage)
                .setFix(fix);

        return GsonUtils.responseSimpleJson(bikeInfoDao.updateBikeInfo(bikeInfo) > 0);
    }

    @Override
    public String updateBikeFix(String bikeId, String fix) throws Exception {
        BikeInfo bikeInfo = bikeInfoDao.searchBikeId(bikeId);
        bikeInfo.setUserId("");
        bikeInfo.setFix(fix);

        return GsonUtils.responseSimpleJson(bikeInfoDao.updateBikeInfo(bikeInfo) > 0);
    }

    @Override
    public String updateBikeLocation(String bikeId, double latitude, double longitude) throws Exception {
        BikeInfo bikeInfo = bikeInfoDao.searchBikeId(bikeId);
        bikeInfo.setLatitude(latitude);
        bikeInfo.setLongitude(longitude);

        return GsonUtils.responseSimpleJson(bikeInfoDao.updateBikeInfo(bikeInfo) > 0);
    }

    @Override
    public String deleteBikeInfo(String bikeId) throws Exception {
        BikeInfo bikeInfo = bikeInfoDao.searchBikeId(bikeId);
        return GsonUtils.responseSimpleJson(bikeInfoDao.deleteBikeInfo(bikeInfo) > 0);
    }

    @Override
    public String deleteMoreBikeInfo(String[] bikeIds) throws Exception {
        boolean b = true;
        for (String id : bikeIds) {
            BikeInfo bikeInfo = bikeInfoDao.searchBikeId(id);
            boolean b1 = bikeInfoDao.deleteBikeInfo(bikeInfo) > 0;
            if (!b1) {
                b = false;
            }
        }
        return GsonUtils.responseSimpleJson(b);
    }
}
