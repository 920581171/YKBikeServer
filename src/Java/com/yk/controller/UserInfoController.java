package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.Utils.ImageUtils;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Api(description = "userInfo")
@Controller
@RequestMapping("userinfo")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoService;

    @ResponseBody
    @ApiOperation(value = "上传头像", httpMethod = "POST")
    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    public String uploadAvatar(@RequestParam("file") MultipartFile original, @RequestParam("userId") String userId) {
        String[] paths = {"D:\\Avatar\\Source\\" + userId + ".jpg",
                "D:\\Avatar\\High\\" + userId + ".jpg",
                "D:\\Avatar\\Middle\\" + userId + ".jpg",
                "D:\\Avatar\\Low\\" + userId + ".jpg"};
        int[] size = {0, 512, 256, 128};

        try {
            for (int i = 0; i < paths.length; i++) {
                File file = new File(paths[i]);
                if (!file.getParentFile().exists())
                    if (!file.getParentFile().mkdirs())
                        return GsonUtils.responseErrorJson();

                if (!file.exists()) {
                    if (!file.createNewFile())
                        return GsonUtils.responseErrorJson();
                }

                if (i == 0) {
                    original.transferTo(file);
                } else {
                    ImageUtils.scale2(paths[0], paths[i], size[i], size[i], true);
                }
            }
            return GsonUtils.responseSuccessJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return GsonUtils.responseErrorJson();
    }

    @ResponseBody
    @ApiOperation(value = "下载头像",httpMethod = "GET")
    @RequestMapping(value = "/downloadAvatar",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadAvatar(@RequestParam("userId")String userId,@RequestParam("level")String level){
        try {
        File file = new File("D:\\Avatar\\Source\\" + userId+".jpg");
        InputStream in = new FileInputStream(file);
            byte[] b=new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            String filename = new String(file.getName().getBytes("gbk"),"iso8859-1");
            headers.add("Content-Disposition", "attachment;filename="+filename);
            HttpStatus statusCode=HttpStatus.OK;
            in.close();
            return new ResponseEntity<byte[]>(b, headers, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @ApiOperation(value = "根据UserId查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserId", method = RequestMethod.POST)
    public String findUserByUserId(@RequestParam("userId") String userId) {
        UserInfo userInfo = userInfoService.searchUserId(userId);
        return (userInfo != null) ?
                GsonUtils.responseSuccessJson(userInfo) :
                GsonUtils.responseErrorJson();
    }

    @ResponseBody
    @ApiOperation(value = "根据UserName查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST)
    public String findUserByUserName(@RequestParam("userName") String userName) {
        UserInfo userInfo = userInfoService.searchUserName(userName);
        return (userInfo != null) ?
                GsonUtils.responseSuccessJson(userInfo) :
                GsonUtils.responseErrorJson();
    }

    @ResponseBody
    @ApiOperation(value = "根据UserPhone查找用户", httpMethod = "POST")
    @RequestMapping(value = "/findUserByUserPhone", method = RequestMethod.POST)
    public String findUserByUserPhone(@RequestParam("userPhone") String userPhone) {
        UserInfo userInfo = userInfoService.searchUserPhone(userPhone);
        return (userInfo != null) ?
                GsonUtils.responseSuccessJson(userInfo) :
                GsonUtils.responseErrorJson();
    }

    @ResponseBody
    @ApiOperation(value = "通过手机号注册用户", httpMethod = "POST")
    @RequestMapping(value = "/registerUserByPhone", method = RequestMethod.POST)
    public String registerUserByPhone(@RequestParam("userPhone") String userPhone) {
        return (userInfoService.addUserByPhone(userPhone) > 0) ?
                GsonUtils.responseSuccessJson() :
                GsonUtils.responseErrorJson();
    }

    @ResponseBody
    @ApiOperation(value = "通过用户名和密码注册用户", httpMethod = "POST")
    @RequestMapping(value = "/registerUserByName", method = RequestMethod.POST)
    public String registerUserByName(@RequestParam("userName") String userName,
                                     @RequestParam("userPassword") String userPassword) {

        return (userInfoService.addUserByName(userName, userPassword) > 0) ?
                GsonUtils.responseSuccessJson() :
                GsonUtils.responseErrorJson();
    }

    @ResponseBody
    @ApiOperation(value = "更新用户信息", httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(@RequestParam("userId") String userId,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("userPhone") String userPhone,
                                 @RequestParam("userPassword") String userPassword,
                                 @RequestParam("userSex") String userSex) {

        UserInfo userInfo = new UserInfo()
                .setUserId(userId)
                .setUserName(userName)
                .setUserPhone(userPhone)
                .setUserPassword(userPassword)
                .setUserSex(userSex);

        return (userInfoService.updateUserInfo(userInfo) > 0) ?
                GsonUtils.responseSuccessJson() :
                GsonUtils.responseErrorJson();
    }

    @ResponseBody
    @ApiOperation(value = "通过userId删除用户",httpMethod = "POST")
    @RequestMapping(value = "/deleteUserInfo",method = RequestMethod.POST)
    public String deleteUserInfo(@RequestParam("userId")String userId){
        UserInfo userInfo = new UserInfo().setUserId(userId);
        return (userInfoService.deleteUserInfo(userInfo) > 0) ?
                GsonUtils.responseSuccessJson() :
                GsonUtils.responseErrorJson();
    }
}
