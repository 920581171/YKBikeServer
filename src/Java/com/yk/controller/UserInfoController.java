package com.yk.controller;

import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("userinfo")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoService;

    @RequestMapping(value = "/registerUserByName",method = RequestMethod.GET)
    public void registerUserByName(@RequestParam("userName")String userName,
                                   @RequestParam("userPassword")String userPassword,
                                   HttpServletResponse rp){

        userInfoService.addUserName(userName,userPassword);

//        try {
//            userInfoService.addUserName(username,password);
//            rp.setContentType("text/javascript;charset=utf-8"); // 设置响应报文的编码格式
//            PrintWriter printWriter = resp.getWriter(); // 获取 response 的输出流
//            printWriter.println(username+password); // 通过输出流把业务逻辑的结果输出
//            printWriter.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
