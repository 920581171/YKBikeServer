package com.yk.bike.controller;

import com.yk.bike.service.ScoreRecordService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "积分信息相关")
@Controller
@RequestMapping(value = "/scorerecord")
public class ScoreRecordController {
    @Autowired
    private ScoreRecordService scoreRecordService;

    @ResponseBody
    @ApiOperation(value = "根据用户Id查询积分记录", httpMethod = "POST")
    @RequestMapping(value = "/findScoreRecordByUserId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findScoreRecordByUserId(@RequestParam("userId") String userId) {
        try {
            return scoreRecordService.findScoreRecordByUserId(userId);
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
            return scoreRecordService.addScoreRecord(userId, score);
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
            return scoreRecordService.updateScoreRecord(recordId);
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
            return scoreRecordService.deleteBalanceRecord(recordId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
