package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "userInfo")
@Controller
@RequestMapping("userinfo")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoService;

    @ResponseBody
    @ApiOperation(value = "app端用户登陆", httpMethod = "POST")
    @RequestMapping(value = "appLogin", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String appLogin(@RequestParam("userName") String userName,
                           @RequestParam("userPassword") String userPassword) {
        try {
            UserInfo userInfo = userInfoService.searchUserName(userName);
            if (userInfo == null) {
                return GsonUtils.responseErrorMsgJson("用户不存在");
            } else if (!userInfo.getUserPassword().equals(userPassword)) {
                return GsonUtils.responseErrorMsgJson("用户名或密码错误");
            } else {
                return GsonUtils.responseSuccessObjJson(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据UserId查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserId", method = RequestMethod.POST)
    public String findUserByUserId(@RequestParam("userId") String userId) {
        UserInfo userInfo = null;
        try {
            userInfo = userInfoService.searchUserId(userId);
            return GsonUtils.responseObjectJson(userInfo != null, userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "查找所有用户信息", httpMethod = "POST")
    @RequestMapping(value = "/findAllUserInfo", method = RequestMethod.POST)
    public String findAllUserInfo() {
        try {
            List<UserInfo> userInfos = userInfoService.searchAllUserInfo();
            return GsonUtils.responseObjectJson(userInfos != null, userInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据UserName查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST)
    public String findUserByUserName(@RequestParam("userName") String userName) {
        try {
            UserInfo userInfo = userInfoService.searchUserName(userName);
            return GsonUtils.responseObjectJson(userInfo != null, userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据UserPhone查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserPhone", method = RequestMethod.POST)
    public String findUserByUserPhone(@RequestParam("userPhone") String userPhone) {
        try {
            UserInfo userInfo = userInfoService.searchUserPhone(userPhone);
            return GsonUtils.responseObjectJson(userInfo != null, userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "通过手机号注册用户", httpMethod = "POST")
    @RequestMapping(value = "/registerUserByPhone", method = RequestMethod.POST)
    public String registerUserByPhone(@RequestParam("userPhone") String userPhone) {
        try {
            return GsonUtils.responseObjectJson(userInfoService.addUserByPhone(userPhone) > 0, userInfoService.searchUserPhone(userPhone));
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "通过用户名和密码注册用户", httpMethod = "POST")
    @RequestMapping(value = "/registerUserByName", method = RequestMethod.POST)
    public String registerUserByName(@RequestParam("userName") String userName,
                                     @RequestParam("userPassword") String userPassword) {
        try {
            return GsonUtils.responseObjectJson(userInfoService.addUserByName(userName, userPassword) > 0, userInfoService.searchUserName(userName));
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新用户信息", httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(@RequestParam("userId") String userId,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("userPhone") String userPhone,
                                 @RequestParam("userPassword") String userPassword,
                                 @RequestParam("deposit") float deposit,
                                 @RequestParam("balance") float balance) {

        try {
            UserInfo userInfo = new UserInfo()
                    .setUserId(userId)
                    .setUserName(userName)
                    .setUserPhone(userPhone)
                    .setUserPassword(userPassword)
                    .setDeposit(deposit)
                    .setBalance(balance);
            return GsonUtils.responseSimpleJson(userInfoService.updateUserInfo(userInfo) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "通过userId删除用户", httpMethod = "POST")
    @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST)
    public String deleteUserInfo(@RequestParam("userId") String userId) {
        try {
            UserInfo userInfo = new UserInfo().setUserId(userId);
            return GsonUtils.responseSimpleJson(userInfoService.deleteUserInfo(userInfo) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
