package com.yk.bike.controller;

import com.yk.bike.service.UserInfoService;
import com.yk.bike.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "用户信息相关")
@Controller
@RequestMapping("userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @ApiOperation(value = "app端用户登陆", httpMethod = "POST")
    @RequestMapping(value = "appLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String appLogin(@RequestParam("userName") String userName,
                           @RequestParam("userPassword") String userPassword) {

        try {
            return userInfoService.appLogin(userName, userPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据UserId查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findUserByUserId(@RequestParam("userId") String userId) {
        try {
            return userInfoService.findUserByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "查找申请退还押金的用户", httpMethod = "POST")
    @RequestMapping(value = "/findDeposit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findDeposit() {
        try {
            return userInfoService.findDeposit();
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "查找所有用户信息", httpMethod = "POST")
    @RequestMapping(value = "/findAllUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllUserInfo() {
        try {
            return userInfoService.findAllUserInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据UserName查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findUserByUserName(@RequestParam("userName") String userName) {
        try {
            return userInfoService.findUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据UserPhone查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserPhone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findUserByUserPhone(@RequestParam("userPhone") String userPhone) {
        try {
            return userInfoService.findUserByUserPhone(userPhone);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "通过手机号注册用户", httpMethod = "POST")
    @RequestMapping(value = "/registerUserByPhone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String registerUserByPhone(@RequestParam("userPhone") String userPhone) {
        try {
            return userInfoService.registerUserByPhone(userPhone);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "通过用户名和密码注册用户", httpMethod = "POST")
    @RequestMapping(value = "/registerUserByName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String registerUserByName(@RequestParam("userName") String userName,
                                     @RequestParam("userPassword") String userPassword) {
        try {
            return userInfoService.registerUserByName(userName, userPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新用户信息", httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateUserInfo(@RequestParam("userId") String userId,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("userPhone") String userPhone,
                                 @RequestParam("userPassword") String userPassword,
                                 @RequestParam("score") int score,
                                 @RequestParam("deposit") float deposit,
                                 @RequestParam("balance") float balance) {

        try {
            return userInfoService.updateUserInfo(userId, userName, userPhone, userPassword, score, deposit, balance);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "重置用户密码", httpMethod = "POST")
    @RequestMapping(value = "/resetUserPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String resetUserPassword(@RequestParam("userId") String userId) {
        try {
            return userInfoService.resetUserPassword(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "通过userId删除用户", httpMethod = "POST")
    @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteUserInfo(@RequestParam("userId") String userId) {
        try {
            return userInfoService.deleteUserInfo(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
