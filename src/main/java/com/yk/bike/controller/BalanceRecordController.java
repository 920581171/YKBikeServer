package com.yk.bike.controller;

import com.yk.bike.service.BalanceRecordService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "余额信息相关")
@Controller
@RequestMapping(value = "/balancerecord")
public class BalanceRecordController {

    @Autowired
    private BalanceRecordService balanceRecordService;

    @ResponseBody
    @ApiOperation(value = "根据用户Id查询余额记录", httpMethod = "POST")
    @RequestMapping(value = "/findBalanceRecordByUserId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findBalanceRecordByUserId(@RequestParam("userId") String userId) {
        try {
            return balanceRecordService.findBalanceRecordByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "查询所有余额记录", httpMethod = "POST")
    @RequestMapping(value = "/findAllBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllBalanceRecord() {
        try {
            return balanceRecordService.findAllBalanceRecord();
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据时间范围查询所有余额记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findAllDateBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllDateBalanceRecord(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        try {
            return balanceRecordService.findAllDateBalanceRecord(startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查询所有余额记录", httpMethod = "POST")
    @RequestMapping(value = "/queryPageBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryPageBalanceRecord(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        try {
            return balanceRecordService.queryPageBalanceRecord(pageIndex, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加余额记录", httpMethod = "POST")
    @RequestMapping(value = "/addBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addBalanceRecord(@RequestParam("userId") String userId, @RequestParam("balance") float balance) {
        try {
            return balanceRecordService.addBalanceRecord(userId, balance);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新余额记录", httpMethod = "POST")
    @RequestMapping(value = "/updateBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateBalanceRecord(@RequestParam("recordId") String recordId) {
        try {
            return balanceRecordService.updateBalanceRecord(recordId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除余额记录", httpMethod = "POST")
    @RequestMapping(value = "/deleteBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteBalanceRecord(@RequestParam("recordId") String recordId) {
        try {
            return balanceRecordService.deleteBalanceRecord(recordId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
