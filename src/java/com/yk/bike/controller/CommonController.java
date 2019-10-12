package com.yk.bike.controller;

import com.yk.bike.service.CommonService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "其他")
@Controller
@RequestMapping(value = "common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "获得服务器时间", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/getServiceTime", method = RequestMethod.POST)
    public String getServiceTime() {
        return commonService.getServiceTime();
    }

    @ResponseBody
    @ApiOperation(value = "上传头像", httpMethod = "POST")
    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    public String uploadAvatar(@RequestParam("file") MultipartFile original, @RequestParam("id") String id) {
        try {
            return commonService.uploadAvatar(original, id);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "下载头像", httpMethod = "GET")
    @RequestMapping(value = "/downloadAvatar", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadAvatar(@RequestParam("id") String id, @RequestParam(value = "level", required = false) String level) {
        try {
            return commonService.downloadAvatar(id, level);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "导出骑行记录EXCEL表", httpMethod = "GET")
    @RequestMapping(value = "/exportBikeRecord")
    @ResponseBody
    public ResponseEntity<byte[]> exportBikeRecord() throws Exception {
        return commonService.exportBikeRecord();
    }

    @ApiOperation(value = "导出押金记录EXCEL表", httpMethod = "GET")
    @RequestMapping(value = "/exportDepositRecord")
    @ResponseBody
    public ResponseEntity<byte[]> exportDepositRecord() throws Exception {
        return commonService.exportDepositRecord();
    }

    @ApiOperation(value = "导出余额统计EXCEL表", httpMethod = "GET")
    @RequestMapping(value = "/exportBalanceRecord")
    @ResponseBody
    public ResponseEntity<byte[]> exportBalanceRecord() throws Exception {
        return commonService.exportBalanceRecord();
    }

    @ApiOperation(value = "批量生成二维码", httpMethod = "GET")
    @RequestMapping(value = "/createQRCode")
    @ResponseBody
    public ResponseEntity<byte[]> createQRCode(@RequestParam("startNum") int startNum, @RequestParam("endNum") int endNum, @RequestParam("bikeType") String bikeType) {
        try {
            return commonService.createQRCode(startNum, endNum, bikeType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
