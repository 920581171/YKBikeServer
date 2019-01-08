package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.AdminInfoServiceImpl;
import com.yk.pojo.AdminInfo;
import com.yk.pojo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description = "adminInfo")
@Controller
@RequestMapping("adminInfo")
public class AdminInfoController {
    @Autowired
    AdminInfoServiceImpl adminInfoService;

    @ResponseBody
    @ApiOperation(value = "根据adminId查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByUserId", method = RequestMethod.POST)
    public String findAdminByUserId(@RequestParam("adminId") String adminId) {
        AdminInfo adminInfo = adminInfoService.searchAdminId(adminId);
        return GsonUtils.responseObjectJson(adminInfo != null,adminInfo);
    }

    @ResponseBody
    @ApiOperation(value = "查找所有管理员信息", httpMethod = "POST")
    @RequestMapping(value = "/findAllAdminInfo", method = RequestMethod.POST)
    public String findAllAdminInfo() {
        List<AdminInfo> adminInfos = adminInfoService.searchAllAdminInfo();
        return GsonUtils.responseObjectJson(adminInfos != null,adminInfos);
    }

    @ResponseBody
    @ApiOperation(value = "根据AdminName查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByUserName", method = RequestMethod.POST)
    public String findAdminByUserName(@RequestParam("adminName") String adminName) {
        AdminInfo adminInfo = adminInfoService.searchAdminName(adminName);
        return GsonUtils.responseObjectJson(adminInfo != null,adminInfo);
    }

    @ResponseBody
    @ApiOperation(value = "根据AdminPhone查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByUserPhone", method = RequestMethod.POST)
    public String findUserByUserPhone(@RequestParam("adminPhone") String adminPhone) {
        AdminInfo adminInfo = adminInfoService.searchAdminPhone(adminPhone);
        return GsonUtils.responseObjectJson(adminInfo != null,adminInfo);
    }

    @ResponseBody
    @ApiOperation(value = "添加管理员", httpMethod = "POST")
    @RequestMapping(value = "/registerAdminInfo", method = RequestMethod.POST)
    public String registerAdminInfo(@RequestParam("adminName") String adminName,
                                    @RequestParam("adminPassword") String adminPassword,
                                     @RequestParam("adminPhone") String adminPhone) {
        return GsonUtils.responseSimpleJson(adminInfoService.addAdminInfo(adminName, adminPassword,adminPhone) > 0);
    }

    @ResponseBody
    @ApiOperation(value = "更新管理员信息", httpMethod = "POST")
    @RequestMapping(value = "/updateAdminInfo", method = RequestMethod.POST)
    public String updateAdminInfo(@RequestParam("adminId") String adminId,
                                 @RequestParam("adminName") String adminName,
                                 @RequestParam("adminPhone") String adminPhone,
                                 @RequestParam("adminPassword") String adminPassword) {

        AdminInfo adminInfo = new AdminInfo()
                .setAdminId(adminId)
                .setAdminName(adminName)
                .setAdminPhone(adminPhone)
                .setAdminPassword(adminPassword);

        return GsonUtils.responseSimpleJson(adminInfoService.updateAdminInfo(adminInfo) > 0);
    }

    @ResponseBody
    @ApiOperation(value = "通过AdminId删除用户",httpMethod = "POST")
    @RequestMapping(value = "/deleteUserInfo",method = RequestMethod.POST)
    public String deleteUserInfo(@RequestParam("adminId")String adminId){
        AdminInfo adminInfo = new AdminInfo().setAdminId(adminId);
        return GsonUtils.responseSimpleJson(adminInfoService.deleteAdminInfo(adminInfo) > 0);
    }
}
