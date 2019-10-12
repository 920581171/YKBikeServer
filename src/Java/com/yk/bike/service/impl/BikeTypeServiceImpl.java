package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.BikeTypeDaoImpl;
import com.yk.bike.pojo.BikeType;
import com.yk.bike.service.BikeTypeService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeTypeServiceImpl implements BikeTypeService {

    @Autowired
    private BikeTypeDaoImpl bikeTypeDao;

    @Override
    public String findAllBikeType() throws Exception {
        List<BikeType> bikeTypes = bikeTypeDao.searchAllBikeType();
        return GsonUtils.responseObjectJson(bikeTypes != null, bikeTypes);
    }
}
