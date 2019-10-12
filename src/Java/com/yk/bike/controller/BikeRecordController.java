package com.yk.bike.controller;


import com.yk.bike.service.BikeRecordService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "记录相关")
@Controller
@RequestMapping(value = "bikeRecord")
public class BikeRecordController {

    @Autowired
    private BikeRecordService bikeRecordService;

    @ApiOperation(value = "查找正在骑行的记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findBikeRecordIsCycling", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findBikeRecordIsCycling(@RequestParam("userId") String userId) {
        try {
            return bikeRecordService.findBikeRecordIsCycling(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据订单id查找记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findBikeRecordByOrderId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findBikeRecordByOrderId(@RequestParam("orderId") String orderId) {
        try {
            return bikeRecordService.findBikeRecordByOrderId(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据用户id查找记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findBikeRecordByUserId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findBikeRecordByUserId(@RequestParam("userId") String userId) {
        try {
            return bikeRecordService.findBikeRecordByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据自行车id查找记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findBikeRecordByBikeId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findBikeRecordByBikeId(@RequestParam("bikeId") String bikeId) {
        try {
            return bikeRecordService.findBikeRecordByBikeId(bikeId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "查找所有记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findAllBikeRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllBikeRecord() {
        try {
            return bikeRecordService.findAllBikeRecord();
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据时间范围查找所有记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findAllDateBikeRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllDateBikeRecord(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        try {
            return bikeRecordService.findAllDateBikeRecord(startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "分页查找所有记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/queryPageBikeRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryPageBikeRecord(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        try {
            return bikeRecordService.queryPageBikeRecord(pageIndex, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加新记录", httpMethod = "POST")
    @RequestMapping(value = "/addBikeRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addBikeRecord(@RequestParam("userId") String userId,
                                @RequestParam("bikeId") String bikeId,
                                @RequestParam("bikeType") String bikeType) {

        try {
            return bikeRecordService.addBikeRecord(userId, bikeId, bikeType);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新记录", httpMethod = "POST")
    @RequestMapping(value = "/updateBikeRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateBikeRecord(@RequestParam("orderId") String orderId,
                                   @RequestParam("userId") String userId,
                                   @RequestParam("bikeId") String bikeId,
                                   @RequestParam("bikeType") String bikeType,
                                   @RequestParam("charge") float charge,
                                   @RequestParam("mileage") float mileage,
                                   @RequestParam("endTime") long endTime,
                                   @RequestParam("orderStatus") String orderStatus) {

        try {
            return bikeRecordService.updateBikeRecord(orderId, userId, bikeId, bikeType, charge, mileage, endTime, orderStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "结束骑行", httpMethod = "POST")
    @RequestMapping(value = "/finishBike", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String finishBike(@RequestParam("orderId") String orderId, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        try {
            return bikeRecordService.finishBike(orderId, latitude, longitude);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除记录", httpMethod = "POST")
    @RequestMapping(value = "/deleteBikeRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteBikeRecord(@RequestParam("orderId") String orderId) {
        try {
            return bikeRecordService.deleteBikeRecord(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
