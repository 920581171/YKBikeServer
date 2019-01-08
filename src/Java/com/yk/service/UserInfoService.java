package com.yk.service;

import com.yk.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo searchUserId(String userId);
    UserInfo searchUserName(String userName);
    UserInfo searchUserPhone(String userPhone);
    List<UserInfo> searchAllUserInfo();
    int addUserByName(String userName, String userPassword);
    int addUserByPhone(String userPhone);
    int updateUserInfo(UserInfo userInfo);
    int deleteUserInfo(UserInfo userInfo);
}
