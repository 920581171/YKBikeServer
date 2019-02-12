package com.yk.service;

import com.yk.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo searchUserId(String userId) throws Exception;

    UserInfo searchUserName(String userName) throws Exception;

    UserInfo searchUserPhone(String userPhone) throws Exception;

    List<UserInfo> searchAllUserInfo() throws Exception;

    List<UserInfo> searchDeposit() throws Exception;

    int addUserByName(String userName, String userPassword) throws Exception;

    int addUserByPhone(String userPhone) throws Exception;

    int updateUserInfo(UserInfo userInfo) throws Exception;

    int deleteUserInfo(UserInfo userInfo) throws Exception;
}
