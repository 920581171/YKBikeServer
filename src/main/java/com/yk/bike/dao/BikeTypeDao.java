package com.yk.bike.dao;

import com.yk.bike.pojo.BikeType;

import java.util.List;

public interface BikeTypeDao {
    BikeType searchTypeId(String typeId) throws Exception;

    List<BikeType> searchAllBikeType() throws Exception;

    List<BikeType> queryPageBikeType(int pageIndex, int pageSize) throws Exception;

    int addBikeType(String typeValue, String typeName,float unitPrice) throws Exception;

    int updateBikeType(BikeType bikeType) throws Exception;

    int deleteBikeType(BikeType bikeType) throws Exception;
}
