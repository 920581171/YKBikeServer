package com.yk.bike.controller;

import com.yk.bike.service.AdminInfoService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "管理员信息相关")
@Controller
@RequestMapping(value = "adminInfo")
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    @ResponseBody
    @ApiOperation(value = "app端管理员登陆", httpMethod = "POST")
    @RequestMapping(value = "/appAdminLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String appAdminLogin(@RequestParam("adminAccountOrPhone") String adminAccountOrPhone,
                                @RequestParam("adminPassword") String adminPassword) {
        try {
            return adminInfoService.appAdminLogin(adminAccountOrPhone, adminPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据adminId查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByAdminId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAdminByAdminId(@RequestParam("adminId") String adminId) {
        try {
            return adminInfoService.findAdminByAdminId(adminId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据adminAccount查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByAdminAccount", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findAdminByAdminAccount(@RequestParam("adminAccount") String adminAccount) {
        try {
            return adminInfoService.findAdminByAdminAccount(adminAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "查找所有管理员信息", httpMethod = "POST")
    @RequestMapping(value = "/findAllAdminInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllAdminInfo() {
        try {
            return adminInfoService.findAllAdminInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据AdminName查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByAdminName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAdminByAdminName(@RequestParam("adminName") String adminName) {
        try {
            return adminInfoService.findAdminByAdminName(adminName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据AdminPhone查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByAdminPhone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findUserByAdminPhone(@RequestParam("adminPhone") String adminPhone) {
        try {
            return adminInfoService.findUserByAdminPhone(adminPhone);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加管理员", httpMethod = "POST")
    @RequestMapping(value = "/registerAdminInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String registerAdminInfo(@RequestParam("adminAccount") String adminAccount,
                                    @RequestParam("adminName") String adminName,
                                    @RequestParam("adminPassword") String adminPassword,
                                    @RequestParam("adminPhone") String adminPhone) {

        try {
            return adminInfoService.registerAdminInfo(adminAccount, adminName, adminPassword, adminPhone);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新管理员信息", httpMethod = "POST")
    @RequestMapping(value = "/updateAdminInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateAdminInfo(@RequestParam("adminId") String adminId,
                                  @RequestParam("adminAccount") String adminAccount,
                                  @RequestParam("adminName") String adminName,
                                  @RequestParam("adminPhone") String adminPhone,
                                  @RequestParam("adminPassword") String adminPassword) {

        try {
            return adminInfoService.updateAdminInfo(adminId, adminAccount, adminName, adminPhone, adminPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "通过AdminId删除用户", httpMethod = "POST")
    @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteUserInfo(@RequestParam("adminId") String adminId) {
        try {
            return adminInfoService.deleteUserInfo(adminId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
