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

    /**
     * 根据用户名添加用户
     * @return 自增的id
     */
    @Override
    public int addUserByName(String userName, String userPassword) {
        String userId = randomUserId();
        while (userInfoDao.selectByUserId(userId) != null) {
            userId = randomUserId();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId)
                .setUserName(userName)
                .setUserPassword(userPassword);
        userInfoDao.insertUser(userInfo);

        return userInfo.getId();
    }

    /**
     * 根据手机号码添加用户
     * @return 自增的id
     */
    @Override
    public int addUserByPhone(String userPhone) {
        String userId = randomUserId();
        while (userInfoDao.selectByUserId(userId) != null) {
            userId = randomUserId();
        }

        UserInfo userInfo = new UserInfo()
                .setUserId(userId)
                .setUserPhone(userPhone);
        userInfoDao.insertUser(userInfo);

        return userInfo.getId();
    }

    /**
     * 根据userId 更新用户信息
     * @return 更改的条数
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    /**
     * 创建随机userId
     */
    private String randomUserId() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder(dateFormat.format(date));
        int random = (int) (Math.random() * 10000);
        stringBuilder.append(random);

        return stringBuilder.toString();
    }
}
