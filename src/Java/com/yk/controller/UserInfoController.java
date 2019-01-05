package com.yk.controller;

import com.yk.Utils.ResponseUtils;
import com.yk.constant.Consts;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.UserInfo;
import com.yk.response.ErrorCommonResponse;
import com.yk.response.SuccessCommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Api(description = "userInfo")
@Controller
@RequestMapping("userinfo")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoService;

    @ApiOperation(value="根据UserId查找用户",httpMethod="POST")
    @RequestMapping(value = "/findUserByUserId", method = RequestMethod.POST)
    public void findUserByUserId(@RequestParam("userId") String userId,
                                 HttpServletResponse rp){

        UserInfo userInfo = userInfoService.searchUserId(userId);
        if (userInfo!=null){
            ResponseUtils.print(rp,new SuccessCommonResponse(userInfo));
        }else{
            ResponseUtils.print(rp,new ErrorCommonResponse());
        }
    }

    @ApiOperation(value="根据UserName查找用户",httpMethod="POST")
    @RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST)
    public void findUserByUserName(@RequestParam("userName") String userName,
                                 HttpServletResponse rp){

        UserInfo userInfo = userInfoService.searchUserName(userName);
        if (userInfo!=null){
            ResponseUtils.print(rp,new SuccessCommonResponse(userInfo));
        }else{
            ResponseUtils.print(rp,new ErrorCommonResponse());
        }
    }

    @ApiOperation(value="根据UserPhone查找用户",httpMethod="POST")
    @RequestMapping(value = "/findUserByUserPhone", method = RequestMethod.POST)
    public void findUserByUserPhone(@RequestParam("userPhone") String userPhone,
                                 HttpServletResponse rp){

        UserInfo userInfo = userInfoService.searchUserPhone(userPhone);
        if (userInfo!=null){
            ResponseUtils.print(rp,new SuccessCommonResponse(userInfo));
        }else{
            ResponseUtils.print(rp,new ErrorCommonResponse());
        }
    }

    @ApiOperation(value="通过手机号注册用户",httpMethod="POST")
    @RequestMapping(value = "/registerUserByPhone", method = RequestMethod.POST)
    public void registerUserByPhone(@RequestParam("userPhone") String userPhone,
                                    HttpServletResponse rp) {

        if (userInfoService.addUserByPhone(userPhone) > 0) {
            ResponseUtils.print(rp, new SuccessCommonResponse());
        } else {
            ResponseUtils.print(rp, new ErrorCommonResponse());
        }
    }

    @ApiOperation(value="通过用户名和密码注册用户",httpMethod="POST")
    @RequestMapping(value = "/registerUserByName", method = RequestMethod.POST)
    public void registerUserByName(@RequestParam("userName") String userName,
                                   @RequestParam("userPassword") String userPassword,
                                   HttpServletResponse rp) {

        if (userInfoService.addUserByName(userName, userPassword) > 0) {
            ResponseUtils.print(rp, new SuccessCommonResponse());
        } else {
            ResponseUtils.print(rp, new ErrorCommonResponse());
        }
    }

    @ApiOperation(value="更新用户信息",httpMethod="POST")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public void updateUserInfo(@RequestParam("userId") String userId,
                               @RequestParam("userName") String userName,
                               @RequestParam("userPhone") String userPhone,
                               @RequestParam("userPassword") String userPassword,
                               @RequestParam("userSex") String userSex,
                               HttpServletResponse rp) {

        UserInfo userInfo = new UserInfo()
                .setUserId(userId)
                .setUserName(userName)
                .setUserPhone(userPhone)
                .setUserPassword(userPassword)
                .setUserSex(userSex);

        if (userInfoService.updateUserInfo(userInfo) > 0) {
            ResponseUtils.print(rp, new SuccessCommonResponse());
        } else {
            ResponseUtils.print(rp, new ErrorCommonResponse());
        }

    }
}
