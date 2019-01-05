package com.yk.dao;

import com.yk.pojo.UserInfo;

public interface UserInfoDao {
    UserInfo selectByUserId(String userId);
    void insertUser(UserInfo userInfo);
    int updateUserInfo(UserInfo userInfo);
}
