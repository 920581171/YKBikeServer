package com.yk.controller;


import com.yk.Utils.GsonUtils;
import com.yk.impl.BikeInfoServiceImpl;
import com.yk.impl.BikeRecordServiceImpl;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.BikeInfo;
import com.yk.pojo.BikeRecord;
import com.yk.pojo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Api(description = "记录相关")
@Controller
@RequestMapping(value = "bikeRecord")
public class BikeRecordController {
    @Autowired
    BikeRecordServiceImpl bikeRecordService;

    @Autowired
    BikeInfoServiceImpl bikeInfoService;

    @Autowired
    UserInfoServiceImpl userInfoService;

    @ApiOperation(value = "查找正在骑行的记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findBikeRecordIsCycling", method = RequestMethod.POST)
    public String findBikeRecordIsCycling(@RequestParam("userId") String userId) {
        try {
            BikeRecord bikeRecord = bikeRecordService.searchCycling(userId);
            return GsonUtils.responseObjectJson(bikeRecord != null, bikeRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据订单id查找记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findBikeRecordByOrderId", method = RequestMethod.POST)
    public String findBikeRecordByOrderId(@RequestParam("orderId") String orderId) {
        try {
            BikeRecord bikeRecord = bikeRecordService.searchOrderId(orderId);
            return GsonUtils.responseObjectJson(bikeRecord != null, bikeRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据用户id查找记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findBikeRecordByUserId", method = RequestMethod.POST)
    public String findBikeRecordByUserId(@RequestParam("userId") String userId) {
        try {
            List<BikeRecord> bikeRecords = bikeRecordService.searchUserId(userId);
            return GsonUtils.responseObjectJson(bikeRecords != null, bikeRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据自行车id查找记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findBikeRecordByBikeId", method = RequestMethod.POST)
    public String findBikeRecordByBikeId(@RequestParam("bikeId") String bikeId) {
        try {
            List<BikeRecord> bikeRecords = bikeRecordService.searchBikeId(bikeId);
            return GsonUtils.responseObjectJson(bikeRecords != null, bikeRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "查找所有记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findAllBikeRecord", method = RequestMethod.POST)
    public String findAllBikeRecord() {
        try {
            List<BikeRecord> bikeRecords = bikeRecordService.searchAllBikeRecord();
            return GsonUtils.responseObjectJson(bikeRecords != null, bikeRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加新记录", httpMethod = "POST")
    @RequestMapping(value = "/addBikeRecord", method = RequestMethod.POST)
    public String addBikeRecord(@RequestParam("userId") String userId,
                                @RequestParam("bikeId") String bikeId) {

        try {
            BikeInfo bikeInfo = bikeInfoService.searchBikeId(bikeId);
            if (bikeInfoService.updateBikeInfo(bikeInfo.setUserId(userId)) > 0) {
                BikeRecord bikeRecord = new BikeRecord()
                        .setUserId(userId)
                        .setBikeId(bikeId)
                        .setCharge(0f)
                        .setMileage(0f)
                        .setCreateTime(new Date(System.currentTimeMillis()))
                        .setEndTime(new Date(System.currentTimeMillis()))
                        .setOrderStatus("0");
                return GsonUtils.responseObjectJson(bikeRecordService.addBikeRecord(bikeRecord) > 0, bikeRecordService.searchById(String.valueOf(bikeRecord.getId())));

            } else {
                return GsonUtils.responseErrorJson();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新记录", httpMethod = "POST")
    @RequestMapping(value = "/updateBikeRecord", method = RequestMethod.POST)
    public String updateBikeRecord(@RequestParam("orderId") String orderId,
                                   @RequestParam("userId") String userId,
                                   @RequestParam("bikeId") String bikeId,
                                   @RequestParam("charge") float charge,
                                   @RequestParam("mileage") float mileage,
                                   @RequestParam("endTime") long endTime,
                                   @RequestParam("orderStatus") String orderStatus) {

        try {
            BikeRecord bikeRecord = bikeRecordService.searchOrderId(orderId);
            bikeRecord.setOrderId(orderId)
                    .setUserId(userId)
                    .setBikeId(bikeId)
                    .setCharge(charge)
                    .setMileage(mileage)
                    .setEndTime(new Date(endTime))
                    .setOrderStatus(orderStatus);

            return GsonUtils.responseSimpleJson(bikeRecordService.updateBikeRecord(bikeRecord) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "结束骑行", httpMethod = "POST")
    @RequestMapping(value = "/finishBike", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String finishBike(@RequestParam("orderId") String orderId) {

        try {
            BikeRecord bikeRecord = bikeRecordService.searchOrderId(orderId);
            BikeInfo bikeInfo = bikeInfoService.searchBikeId(bikeRecord.getBikeId());
            UserInfo userInfo = userInfoService.searchUserId(bikeRecord.getUserId());
            if (bikeInfoService.updateBikeInfo(bikeInfo.setUserId("")) > 0) {
                long createTime = bikeRecord.getCreateTime().getTime();
                long endTime = System.currentTimeMillis();

                float charge = (endTime - createTime) / 1000f / 60f / 10f;
                charge = (float) (Math.round(charge * 100)) / 100;

                bikeRecord.setOrderId(orderId)
                        .setCharge(charge)
                        .setMileage(0f)
                        .setEndTime(new Date(endTime))
                        .setOrderStatus("1");

                if (bikeRecordService.updateBikeRecord(bikeRecord) > 0) {
                    float balance = userInfo.getBalance() - charge;
                    balance = (float) (Math.round(balance * 100)) / 100;

                    boolean b = userInfoService.updateUserInfo(userInfo.setBalance(balance)) > 0;
                    if (b&&balance < 0) {
                        return GsonUtils.responseErrorMsgJson("余额不足");
                    }
                    return GsonUtils.responseObjectJson(b, bikeRecordService.searchOrderId(orderId));
                } else {
                    return GsonUtils.responseErrorJson();
                }
            } else {
                return GsonUtils.responseErrorJson();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除记录", httpMethod = "POST")
    @RequestMapping(value = "/deleteBikeRecord", method = RequestMethod.POST)
    public String deleteBikeRecord(@RequestParam("orderId") String orderId) {
        try {
            BikeRecord bikeRecord = bikeRecordService.searchOrderId(orderId);
            return GsonUtils.responseSimpleJson(bikeRecordService.deleteBikeRecord(bikeRecord) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
