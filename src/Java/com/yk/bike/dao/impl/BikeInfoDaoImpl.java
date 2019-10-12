package com.yk.bike.dao.impl;

import com.yk.bike.mapper.BikeInfoMapper;
import com.yk.bike.pojo.BikeInfo;
import com.yk.bike.dao.BikeInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeInfoDaoImpl implements BikeInfoDao {
    @Autowired
    private BikeInfoMapper bikeInfoDao;

    @Override
    public BikeInfo searchBikeId(String bikeId) throws Exception {
        return bikeInfoDao.selectOne(BikeInfoMapper.COLUMN_BIKE_ID, bikeId);
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
    public int addBikeInfo(String bikeId,String bikeType, double latitude, double longitude) throws Exception {

        BikeInfo bikeInfo = searchBikeId(bikeId);

        if (searchBikeId(bikeId) != null) {
            bikeInfo.setBikeType(bikeType);
            bikeInfo.setUserId("");
            bikeInfo.setLatitude(latitude);
            bikeInfo.setLongitude(longitude);
            bikeInfoDao.update(bikeInfo);
        } else {
            bikeInfo = new BikeInfo()
                    .setUserId("")
                    .setBikeId(bikeId)
                    .setBikeType(bikeType)
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
