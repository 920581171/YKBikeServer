package com.yk.bike.dao.impl;

import com.yk.bike.utils.RandomUtils;
import com.yk.bike.mapper.BikeTypeMapper;
import com.yk.bike.pojo.BikeType;
import com.yk.bike.dao.BikeTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class BikeTypeDaoImpl implements BikeTypeDao {
    @Autowired
    private BikeTypeMapper bikeTypeDao;

    @Override
    public BikeType searchTypeId(String typeId) throws Exception {
        return bikeTypeDao.selectOne(BikeTypeMapper.COLUMN_ID,typeId);
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
        while (bikeTypeDao.selectOne(BikeTypeMapper.COLUMN_TYPE_ID, typeId) != null) {
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
