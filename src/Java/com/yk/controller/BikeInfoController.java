package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.BikeInfoServiceImpl;
import com.yk.pojo.BikeInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description = "车辆信息相关")
@Controller
@RequestMapping("bikeinfo")
public class BikeInfoController {
    @Autowired
    BikeInfoServiceImpl bikeInfoService;

    @ResponseBody
    @ApiOperation(value = "查找所有自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/findAllBikeInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findAllBikeInfo() {
        try {
            List<BikeInfo> bikeInfos = bikeInfoService.searchAllBikeInfo();
            return GsonUtils.responseObjectJson(bikeInfos != null, bikeInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据bikeId查询自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/findBikeInfoByBikeId", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findBikeInfoByBikeId(@RequestParam("bikeId") String bikeId) {
        try {
            BikeInfo bikeInfo = bikeInfoService.searchBikeId(bikeId);
            return GsonUtils.responseObjectJson(bikeInfo != null, bikeInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/addBikeInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addBikeInfo(@RequestParam("bikeId") String bikeId,
                              @RequestParam("latitude") double latitude,
                              @RequestParam("longitude") double longitude) {
        try {
            return GsonUtils.responseSimpleJson(bikeInfoService.addBikeInfo(bikeId, latitude, longitude) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/updateBikeInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateBikeInfo(@RequestParam("bikeId") String bikeId,
                                 @RequestParam(name = "userId", required = false) String userId,
                                 @RequestParam("latitude") double latitude,
                                 @RequestParam("longitude") double longitude,
                                 @RequestParam("mileage") float mileage,
                                 @RequestParam("fix") String fix) {

        try {
            BikeInfo bikeInfo = bikeInfoService.searchBikeId(bikeId);
            bikeInfo.setUserId(userId)
                    .setLatitude(latitude)
                    .setLongitude(longitude)
                    .setMileage(mileage)
                    .setFix(fix);

            return GsonUtils.responseSimpleJson(bikeInfoService.updateBikeInfo(bikeInfo) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新自行车维修状态", httpMethod = "POST")
    @RequestMapping(value = "/updateBikeFix", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateBikeFix(@RequestParam("bikeId") String bikeId,
                                @RequestParam("fix") String fix) {

        try {
            BikeInfo bikeInfo = bikeInfoService.searchBikeId(bikeId);
            bikeInfo.setUserId("");
            bikeInfo.setFix(fix);

            return GsonUtils.responseSimpleJson(bikeInfoService.updateBikeInfo(bikeInfo) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新自行车位置信息", httpMethod = "POST")
    @RequestMapping(value = "/updateBikeLocation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateBikeLocation(@RequestParam("bikeId") String bikeId,
                                     @RequestParam("latitude") double latitude,
                                     @RequestParam("longitude") double longitude) {

        try {
            BikeInfo bikeInfo = bikeInfoService.searchBikeId(bikeId);
            bikeInfo.setLatitude(latitude);
            bikeInfo.setLongitude(longitude);

            return GsonUtils.responseSimpleJson(bikeInfoService.updateBikeInfo(bikeInfo) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除自行车信息", httpMethod = "POST")
    @RequestMapping(value = "/deleteBikeInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String deleteBikeInfo(@RequestParam("bikeId") String bikeId) {
        try {
            BikeInfo bikeInfo = bikeInfoService.searchBikeId(bikeId);
            return GsonUtils.responseSimpleJson(bikeInfoService.deleteBikeInfo(bikeInfo) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
