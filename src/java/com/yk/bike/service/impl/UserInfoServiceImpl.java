package com.yk.bike.service.impl;

import com.yk.bike.dao.impl.UserInfoDaoImpl;
import com.yk.bike.pojo.UserInfo;
import com.yk.bike.service.UserInfoService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDaoImpl userInfoDao;

    @Override
    public String appLogin(String userName, String userPassword) throws Exception {
        UserInfo userInfo = userInfoDao.searchUserName(userName);
        if (userInfo == null) {
            return GsonUtils.responseErrorMsgJson("用户不存在");
        } else if (!userInfo.getUserPassword().equals(userPassword)) {
            return GsonUtils.responseErrorMsgJson("用户名或密码错误");
        } else {
            return GsonUtils.responseSuccessObjJson(userInfo);
        }
    }

    @Override
    public String findUserByUserId(String userId) throws Exception {
        UserInfo userInfo = userInfoDao.searchUserId(userId);
        return GsonUtils.responseObjectJson(userInfo != null, userInfo);
    }

    @Override
    public String findDeposit() throws Exception {
        List<UserInfo> userInfos = userInfoDao.searchDeposit();
        return GsonUtils.responseObjectJson(userInfos != null, userInfos);
    }

    @Override
    public String findAllUserInfo() throws Exception {
        List<UserInfo> userInfos = userInfoDao.searchAllUserInfo();
        return GsonUtils.responseObjectJson(userInfos != null, userInfos);
    }

    @Override
    public String findUserByUserName(String userName) throws Exception {
        UserInfo userInfo = userInfoDao.searchUserName(userName);
        return GsonUtils.responseObjectJson(userInfo != null, userInfo);
    }

    @Override
    public String findUserByUserPhone(String userPhone) throws Exception {
        UserInfo userInfo = userInfoDao.searchUserPhone(userPhone);
        return GsonUtils.responseObjectJson(userInfo != null, userInfo);
    }

    @Override
    public String registerUserByPhone(String userPhone) throws Exception {
        return GsonUtils.responseObjectJson(userInfoDao.addUserByPhone(userPhone) > 0, userInfoDao.searchUserPhone(userPhone));
    }

    @Override
    public String registerUserByName(String userName, String userPassword) throws Exception {
        return GsonUtils.responseObjectJson(userInfoDao.addUserByName(userName, userPassword) > 0, userInfoDao.searchUserName(userName));
    }

    @Override
    public String updateUserInfo(String userId, String userName, String userPhone, String userPassword, int score, float deposit, float balance) throws Exception {
        UserInfo userInfo = new UserInfo()
                .setUserId(userId)
                .setUserName(userName)
                .setUserPhone(userPhone)
                .setUserPassword(userPassword)
                .setScore(score)
                .setDeposit(deposit)
                .setBalance(balance);
        return GsonUtils.responseSimpleJson(userInfoDao.updateUserInfo(userInfo) > 0);
    }

    @Override
    public String resetUserPassword(String userId) throws Exception {
        UserInfo userInfo = userInfoDao.searchUserId(userId);
        if (userInfo == null) {
            return GsonUtils.responseErrorMsgJson("用户不存在");
        }
        userInfo.setUserPassword("25d55ad283aa400af464c76d713c07ad");
        return GsonUtils.responseSimpleJson(userInfoDao.updateUserInfo(userInfo) > 0);
    }

    @Override
    public String deleteUserInfo(String userId) throws Exception {
        UserInfo userInfo = new UserInfo().setUserId(userId);
        return GsonUtils.responseSimpleJson(userInfoDao.deleteUserInfo(userInfo) > 0);
    }
}
