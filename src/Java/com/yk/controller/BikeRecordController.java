package com.yk.controller;


import com.yk.Utils.GsonUtils;
import com.yk.impl.BikeRecordServiceImpl;
import com.yk.pojo.BikeInfo;
import com.yk.pojo.BikeRecord;
import com.yk.service.BikeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Api(value = "bikeRecord")
@Controller
@RequestMapping(value = "bikeRecord")
public class BikeRecordController {
    @Autowired
    BikeRecordServiceImpl bikeRecordService;

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
                                @RequestParam("bikeId") String bikeId,
                                @RequestParam("charge") float charge,
                                @RequestParam("mileage") float mileage,
                                @RequestParam("createTime") long createTime,
                                @RequestParam("endTime") long endTime) {

        try {
            BikeRecord bikeRecord = new BikeRecord()
                    .setUserId(userId)
                    .setBikeId(bikeId)
                    .setCharge(charge)
                    .setMileage(mileage)
                    .setCreateTime(new Timestamp(createTime))
                    .setEndTime(new Timestamp(endTime));

            return GsonUtils.responseSimpleJson(bikeRecordService.addBikeRecord(bikeRecord) > 0);
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
                                   @RequestParam("endTime") long endTime) {

        try {
            BikeRecord bikeRecord = bikeRecordService.searchOrderId(orderId);
            bikeRecord.setOrderId(orderId)
                    .setUserId(userId)
                    .setBikeId(bikeId)
                    .setCharge(charge)
                    .setMileage(mileage)
                    .setEndTime(new Timestamp(endTime));

            return GsonUtils.responseSimpleJson(bikeRecordService.updateBikeRecord(bikeRecord) > 0);
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
