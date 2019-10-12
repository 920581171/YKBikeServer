package com.yk.bike.controller;

import com.yk.bike.service.DepositRecordService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "押金信息相关")
@Controller
@RequestMapping(value = "/depositrecord")
public class DepositRecordController {

    @Autowired
    private DepositRecordService depositRecordService;

    @ResponseBody
    @ApiOperation(value = "根据用户Id查询押金记录", httpMethod = "POST")
    @RequestMapping(value = "/findDepositRecordByUserId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findDepositRecordByUserId(@RequestParam("userId") String userId) {
        try {
            return depositRecordService.findDepositRecordByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据时间范围查找所有押金记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findAllDateDepositRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllDateDepositRecord(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        try {
            return depositRecordService.findAllDateDepositRecord(startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "查询所有押金记录", httpMethod = "POST")
    @RequestMapping(value = "/findAllDepositRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllDepositRecord() {
        try {
            return depositRecordService.findAllDepositRecord();
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查询所有押金记录", httpMethod = "POST")
    @RequestMapping(value = "/queryPageDepositRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryPageDepositRecord(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        try {
            return depositRecordService.queryPageDepositRecord(pageIndex, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加押金记录", httpMethod = "POST")
    @RequestMapping(value = "/addDepositRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addDepositRecord(@RequestParam("userId") String userId, @RequestParam("deposit") float deposit) {
        try {
            return depositRecordService.addDepositRecord(userId, deposit);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新押金记录", httpMethod = "POST")
    @RequestMapping(value = "/updateDepositRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateDepositRecord(@RequestParam("recordId") String recordId) {
        try {
            return depositRecordService.updateDepositRecord(recordId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除押金记录", httpMethod = "POST")
    @RequestMapping(value = "/deleteDepositRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteDepositRecord(@RequestParam("recordId") String recordId) {
        try {
            return depositRecordService.deleteDepositRecord(recordId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
