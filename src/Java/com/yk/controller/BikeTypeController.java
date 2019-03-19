package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.BikeTypeServiceImpl;
import com.yk.pojo.BikeType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description = "车辆类型相关")
@Controller
@RequestMapping(value = "bikeType")
public class BikeTypeController {

    @Autowired
    BikeTypeServiceImpl bikeTypeService;

    @ApiOperation(value = "获取车辆类型")
    @RequestMapping(value = "/findAllBikeType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findAllBikeType() {
        try {
            List<BikeType> bikeTypes = bikeTypeService.searchAllBikeType();
            return GsonUtils.responseObjectJson(bikeTypes != null, bikeTypes);
        } catch (Exception e) {
            return GsonUtils.responseErrorJson();
        }
    }
}
