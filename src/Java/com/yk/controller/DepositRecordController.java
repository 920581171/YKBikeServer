package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.DepositRecordServiceImpl;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.DepositRecord;
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

@Api(description = "押金信息相关")
@Controller
@RequestMapping(value = "/depositrecord")
public class DepositRecordController {
    @Autowired
    DepositRecordServiceImpl depositRecordService;
    @Autowired
    UserInfoServiceImpl userInfoService;

    @ResponseBody
    @ApiOperation(value = "根据用户Id查询押金记录", httpMethod = "POST")
    @RequestMapping(value = "/findDepositRecordByUserId", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findDepositRecordByUserId(@RequestParam("userId") String userId) {
        try {
            List<DepositRecord> depositRecords = depositRecordService.searchDepositRecordByUserId(userId);
            return GsonUtils.responseObjectJson(depositRecords != null, depositRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加押金记录", httpMethod = "POST")
    @RequestMapping(value = "/addDepositRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addDepositRecord(@RequestParam("userId") String userId, @RequestParam("deposit") float deposit) {
        try {
            UserInfo userInfo = userInfoService.searchUserId(userId);
            if (userInfo == null)
                return GsonUtils.responseErrorJson();

            userInfo.setDeposit(deposit);
            boolean b = userInfoService.updateUserInfo(userInfo) > 0;

            if (!b)
                return GsonUtils.responseErrorJson();

            if (deposit == 0)
                deposit = -199;
            DepositRecord depositRecord = new DepositRecord().setUserId(userId)
                    .setDeposit(deposit)
                    .setCreateTime(new Date(System.currentTimeMillis()));

            return GsonUtils.responseSimpleJson(depositRecordService.addDepositRecord(depositRecord) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新押金记录", httpMethod = "POST")
    @RequestMapping(value = "/updateDepositRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateDepositRecord(@RequestParam("recordId") String recordId) {
        try {
            DepositRecord depositRecord = depositRecordService.searchDepositRecordId(recordId);
            return GsonUtils.responseObjectJson(depositRecordService.updateDepositRecord(depositRecord) > 0, depositRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除押金记录", httpMethod = "POST")
    @RequestMapping(value = "/deleteDepositRecord", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String deleteDepositRecord(@RequestParam("recordId") String recordId) {
        try {
            return GsonUtils.responseSimpleJson(depositRecordService.deleteDepositRecord(new DepositRecord().setRecordId(recordId)) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
