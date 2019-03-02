package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.BalanceRecordServiceImpl;
import com.yk.impl.ScoreRecordServiceImpl;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.BalanceRecord;
import com.yk.pojo.ScoreRecord;
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

@Api(description = "积分信息相关")
@Controller
@RequestMapping(value = "/scorerecord")
public class ScoreRecordController {
    @Autowired
    ScoreRecordServiceImpl scoreRecordService;
    @Autowired
    BalanceRecordServiceImpl balanceRecordService;
    @Autowired
    UserInfoServiceImpl userInfoService;

    @ResponseBody
    @ApiOperation(value = "根据用户Id查询积分记录", httpMethod = "POST")
    @RequestMapping(value = "/findScoreRecordByUserId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findScoreRecordByUserId(@RequestParam("userId") String userId) {
        try {
            List<ScoreRecord> scoreRecords = scoreRecordService.searchScoreRecordByUserId(userId);
            return GsonUtils.responseObjectJson(scoreRecords != null, scoreRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加积分记录", httpMethod = "POST")
    @RequestMapping(value = "/addScoreRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addScoreRecord(@RequestParam("userId") String userId, @RequestParam("score") int score) {
        try {
            UserInfo userInfo = userInfoService.searchUserId(userId);
            if (userInfo == null)
                return GsonUtils.responseErrorJson();

            float balance = (float) (Math.round(score / 10f * 100) / 100);
            userInfo.setBalance(userInfo.getBalance() + balance)
                    .setScore(userInfo.getScore() - score);
            boolean b = userInfoService.updateUserInfo(userInfo) > 0;

            if (!b)
                return GsonUtils.responseErrorJson();

            Date createTime = new Date(System.currentTimeMillis());

            BalanceRecord balanceRecord = new BalanceRecord().setUserId(userId)
                    .setBalance(balance)
                    .setCreateTime(createTime)
                    .setIsExchange("1");

            balanceRecordService.addBalanceRecord(balanceRecord);

            ScoreRecord scoreRecord = new ScoreRecord().setUserId(userId)
                    .setScore(-score)
                    .setCreateTime(createTime);

            return GsonUtils.responseSimpleJson(scoreRecordService.addScoreRecord(scoreRecord) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新积分记录", httpMethod = "POST")
    @RequestMapping(value = "/updateScoreRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateScoreRecord(@RequestParam("recordId") String recordId) {
        try {
            ScoreRecord scoreRecord = scoreRecordService.searchScoreRecordId(recordId);
            return GsonUtils.responseObjectJson(scoreRecordService.updateScoreRecord(scoreRecord) > 0, scoreRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除积分记录", httpMethod = "POST")
    @RequestMapping(value = "/deleteBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteBalanceRecord(@RequestParam("recordId") String recordId) {
        try {
            return GsonUtils.responseSimpleJson(scoreRecordService.deleteScoreRecord(new ScoreRecord().setRecordId(recordId)) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
