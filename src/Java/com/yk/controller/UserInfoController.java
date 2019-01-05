package com.yk.controller;

import com.yk.Utils.ResponseUtils;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.UserInfo;
import com.yk.response.ErrorCommonResponse;
import com.yk.response.SuccessCommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("userinfo")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoService;

    @RequestMapping(value = "/registerUserByPhone", method = RequestMethod.POST)
    public void registerUserByPhone(@RequestParam("userPhone") String userPhone,
                                    HttpServletResponse rp) {

        if (userInfoService.addUserByPhone(userPhone) > 0) {
            ResponseUtils.print(rp, new SuccessCommonResponse());
        } else {
            ResponseUtils.print(rp, new ErrorCommonResponse());
        }
    }

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
