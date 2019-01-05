package com.yk.impl;

import com.yk.dao.UserInfoDao;
import com.yk.pojo.UserInfo;
import com.yk.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public UserInfo searchUserId(String userId) {
        return userInfoDao.selectByUserId(userId);
    }

    @Override
    public int addUserName(String userName, String userPassword) {
        String userId = randomUserId();
        while (userInfoDao.selectByUserId(userId) != null) {
            userId = randomUserId();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);
        userInfoDao.insertUser(userInfo);

        return userInfo.getId();
    }

    @Override
    public int addUserPhone(String userPhone) {
        String userId = randomUserId();
        while (userInfoDao.selectByUserId(userId) != null) {
            userId = randomUserId();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserPhone(userPhone);
        userInfoDao.insertUser(userInfo);

        return userInfo.getId();
    }

    private String randomUserId() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder(dateFormat.format(date));
        int random = (int) (Math.random() * 10000);
        stringBuilder.append(random);

        return stringBuilder.toString();
    }
}
