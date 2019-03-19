package com.yk.impl;

import com.yk.Utils.RandomUtils;
import com.yk.dao.BikeTypeDao;
import com.yk.pojo.BikeType;
import com.yk.service.BikeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class BikeTypeServiceImpl implements BikeTypeService {
    @Autowired
    private BikeTypeDao bikeTypeDao;

    @Override
    public BikeType searchTypeId(String typeId) throws Exception {
        return bikeTypeDao.selectOne(BikeTypeDao.COLUMN_ID,typeId);
    }

    @Override
    public List<BikeType> searchAllBikeType() throws Exception {
        return bikeTypeDao.selectTable();
    }

    @Override
    public List<BikeType> queryPageBikeType(int pageIndex, int pageSize) throws Exception {
        return bikeTypeDao.queryPageTable(pageIndex,pageSize);
    }

    @Override
    public int addBikeType(String typeValue, String typeName,float unitPrice) throws Exception {
        String typeId = RandomUtils.randomId("TYPE");
        while (bikeTypeDao.selectOne(BikeTypeDao.COLUMN_TYPE_ID, typeId) != null) {
            typeId = RandomUtils.randomId("TYPE");
        }

        BikeType bikeType = new BikeType();
        bikeType.setTypeId(typeId)
                .setTypeValue(typeValue)
                .setTypeName(typeName)
                .setUnitPrice(unitPrice);

        return bikeType.getId();
    }

    @Override
    public int updateBikeType(BikeType bikeType) throws Exception {
        return bikeTypeDao.update(bikeType);
    }

    @Override
    public int deleteBikeType(BikeType bikeType) throws Exception {
        return bikeTypeDao.delete(bikeType);
    }

    public LinkedHashMap<String,Float> getMapBikeTypeUnitPrice(){
        LinkedHashMap<String,Float> map = new LinkedHashMap<>();
        for (BikeType bikeType:bikeTypeDao.selectTable()){
            map.put(bikeType.getTypeValue(),bikeType.getUnitPrice());
        }
        return map;
    }
}
