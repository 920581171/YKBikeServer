package com.yk.bike.controller;

import com.yk.bike.service.BikeInfoService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "车辆信息相关")
@Controller
@RequestMapping("bikeinfo")
public class BikeInfoController {

    @Autowired
    private BikeInfoService bikeInfoService;

    @ResponseBody
    @ApiOperation(value = "查找所有自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/findAllBikeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllBikeInfo() {
        try {
            return bikeInfoService.findAllBikeInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查找所有自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/queryPageBikeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryPageBikeInfo(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        try {
            return bikeInfoService.queryPageBikeInfo(pageIndex, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据bikeId查询自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/findBikeInfoByBikeId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findBikeInfoByBikeId(@RequestParam("bikeId") String bikeId) {
        try {
            return bikeInfoService.findBikeInfoByBikeId(bikeId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/addBikeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addBikeInfo(@RequestParam("bikeId") String bikeId,
                              @RequestParam("bikeType") String bikeType,
                              @RequestParam("latitude") double latitude,
                              @RequestParam("longitude") double longitude) {
        try {
            return bikeInfoService.addBikeInfo(bikeId, bikeType, latitude, longitude);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/updateBikeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateBikeInfo(@RequestParam("bikeId") String bikeId,
                                 @RequestParam(name = "userId", required = false) String userId,
                                 @RequestParam("latitude") double latitude,
                                 @RequestParam("longitude") double longitude,
                                 @RequestParam("mileage") float mileage,
                                 @RequestParam("fix") String fix) {

        try {
            return bikeInfoService.updateBikeInfo(bikeId, userId, latitude, longitude, mileage, fix);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新自行车维修状态", httpMethod = "POST")
    @RequestMapping(value = "/updateBikeFix", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateBikeFix(@RequestParam("bikeId") String bikeId,
                                @RequestParam("fix") String fix) {

        try {
            return bikeInfoService.updateBikeFix(bikeId, fix);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新自行车位置信息", httpMethod = "POST")
    @RequestMapping(value = "/updateBikeLocation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateBikeLocation(@RequestParam("bikeId") String bikeId,
                                     @RequestParam("latitude") double latitude,
                                     @RequestParam("longitude") double longitude) {

        try {
            return bikeInfoService.updateBikeLocation(bikeId, latitude, longitude);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/deleteBikeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteBikeInfo(@RequestParam("bikeId") String bikeId) {
        try {
            return bikeInfoService.deleteBikeInfo(bikeId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "批量删除自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/deleteMoreBikeInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteMoreBikeInfo(@RequestParam(value = "bikeIds") String[] bikeIds) {
        try {
            return bikeInfoService.deleteMoreBikeInfo(bikeIds);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
