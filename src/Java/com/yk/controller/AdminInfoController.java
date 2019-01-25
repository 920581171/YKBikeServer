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

import javax.naming.Name;
import java.util.List;

@Api(description = "adminInfo")
@Controller
@RequestMapping(value = "adminInfo")
public class AdminInfoController {
    @Autowired
    AdminInfoServiceImpl adminInfoService;

    @ResponseBody
    @ApiOperation(value = "app端管理员登陆", httpMethod = "POST")
    @RequestMapping(value = "/appAdminLogin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String appAdminLogin(@RequestParam("adminAccountOrPhone") String adminAccountOrPhone,
                                @RequestParam("adminPassword") String adminPassword) {
        try {
            AdminInfo adminInfo = (adminInfoService.searchAdminAccount(adminAccountOrPhone) == null) ?
                    adminInfoService.searchAdminPhone(adminAccountOrPhone) :
                    adminInfoService.searchAdminAccount(adminAccountOrPhone);

            if (adminInfo == null) {
                return GsonUtils.responseErrorJson("管理员不存在");
            } else if (!adminInfo.getAdminPassword().equals(adminPassword)) {
                return GsonUtils.responseErrorJson("密码错误");
            } else {
                return GsonUtils.responseSuccessJson(adminInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据adminId查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByAdminId", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findAdminByAdminId(@RequestParam("adminId") String adminId) {
        try {
            AdminInfo adminInfo = adminInfoService.searchAdminId(adminId);
            return GsonUtils.responseObjectJson(adminInfo != null, adminInfo);
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
            AdminInfo adminInfo = adminInfoService.searchAdminAccount(adminAccount);
            return GsonUtils.responseObjectJson(adminInfo != null, adminInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "查找所有管理员信息", httpMethod = "POST")
    @RequestMapping(value = "/findAllAdminInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findAllAdminInfo() {
        try {
            List<AdminInfo> adminInfos = adminInfoService.searchAllAdminInfo();
            return GsonUtils.responseObjectJson(adminInfos != null, adminInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据AdminName查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByAdminName", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findAdminByAdminName(@RequestParam("adminName") String adminName) {
        try {
            AdminInfo adminInfo = adminInfoService.searchAdminName(adminName);
            return GsonUtils.responseObjectJson(adminInfo != null, adminInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据AdminPhone查找管理员", httpMethod = "POST")
    @RequestMapping(value = "/findAdminByAdminPhone", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String findUserByAdminPhone(@RequestParam("adminPhone") String adminPhone) {
        try {
            AdminInfo adminInfo = adminInfoService.searchAdminPhone(adminPhone);
            return GsonUtils.responseObjectJson(adminInfo != null, adminInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加管理员", httpMethod = "POST")
    @RequestMapping(value = "/registerAdminInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String registerAdminInfo(@RequestParam("adminAccount") String adminAccount,
                                    @RequestParam("adminName") String adminName,
                                    @RequestParam("adminPassword") String adminPassword,
                                    @RequestParam("adminPhone") String adminPhone) {
        try {
            return GsonUtils.responseSimpleJson(adminInfoService.addAdminInfo(adminAccount, adminName, adminPassword, adminPhone) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新管理员信息", httpMethod = "POST")
    @RequestMapping(value = "/updateAdminInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String updateAdminInfo(@RequestParam("adminId") String adminId,
                                  @RequestParam("adminAccount") String adminAccount,
                                  @RequestParam("adminName") String adminName,
                                  @RequestParam("adminPhone") String adminPhone,
                                  @RequestParam("adminPassword") String adminPassword) {

        AdminInfo adminInfo = new AdminInfo()
                .setAdminAccount(adminAccount)
                .setAdminId(adminId)
                .setAdminName(adminName)
                .setAdminPhone(adminPhone)
                .setAdminPassword(adminPassword);

        try {
            return GsonUtils.responseSimpleJson(adminInfoService.updateAdminInfo(adminInfo) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "通过AdminId删除用户", httpMethod = "POST")
    @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String deleteUserInfo(@RequestParam("adminId") String adminId) {
        try {
            AdminInfo adminInfo = new AdminInfo().setAdminId(adminId);
            return GsonUtils.responseSimpleJson(adminInfoService.deleteAdminInfo(adminInfo) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
