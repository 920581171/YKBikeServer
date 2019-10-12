package com.yk.bike.controller;

import com.yk.bike.service.BikeTypeService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "车辆类型相关")
@Controller
@RequestMapping(value = "bikeType")
public class BikeTypeController {

    @Autowired
    private BikeTypeService bikeTypeService;

    @ApiOperation(value = "获取车辆类型")
    @RequestMapping(value = "/findAllBikeType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findAllBikeType() {
        try {
            return bikeTypeService.findAllBikeType();
        } catch (Exception e) {
            return GsonUtils.responseErrorJson();
        }
    }
}
