package com.yk.impl;

import com.yk.dao.BaseDao;
import com.yk.dao.UserInfoDao;
import com.yk.pojo.UserInfo;
import com.yk.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    /**
     * 根据userId查找用户
     */
    @Override
    public UserInfo searchUserId(String userId) throws Exception {
        return userInfoDao.selectOne(UserInfoDao.COLUMN_USER_ID, userId);
    }

    /**
     * 根据userName查找用户
     */
    @Override
    public UserInfo searchUserName(String userName) throws Exception {
        return userInfoDao.selectOne(UserInfoDao.COLUMN_USER_NAME, userName);
    }

    /**
     * 根据userPhone查找用户
     */
    @Override
    public UserInfo searchUserPhone(String userPhone) throws Exception {
        return userInfoDao.selectOne(UserInfoDao.COLUMN_USER_PHONE, userPhone);
    }

    @Override
    public List<UserInfo> searchAllUserInfo() throws Exception {
        return userInfoDao.selectTable();
    }

    /**
     * 根据用户名添加用户
     *
     * @return 自增的id
     */
    @Override
    public int addUserByName(String userName, String userPassword) throws Exception {
        String userId = randomUserId();
        while (userInfoDao.selectOne(UserInfoDao.COLUMN_USER_ID, userId) != null) {
            userId = randomUserId();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId)
                .setUserName(userName)
                .setUserPassword(userPassword);
        userInfoDao.insert(userInfo);

        return userInfo.getId();
    }

    /**
     * 根据手机号码添加用户
     *
     * @return 自增的id
     */
    @Override
    public int addUserByPhone(String userPhone) throws Exception {
        String userId = randomUserId();
        while (userInfoDao.selectOne(UserInfoDao.COLUMN_USER_ID, userId) != null) {
            userId = randomUserId();
        }

        UserInfo userInfo = new UserInfo()
                .setUserId(userId)
                .setUserPhone(userPhone);
        userInfoDao.insert(userInfo);

        return userInfo.getId();
    }

    /**
     * 根据userId 更新用户信息
     *
     * @return 更改的条数
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) throws Exception {
        return userInfoDao.update(userInfo);
    }

    /**
     * 根据userId删除数据
     *
     * @return 更改的条数
     */
    @Override
    public int deleteUserInfo(UserInfo userInfo) throws Exception {
        return userInfoDao.delete(userInfo);
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
