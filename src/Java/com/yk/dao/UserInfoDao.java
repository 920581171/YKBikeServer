package com.yk.dao;

import com.yk.pojo.UserInfo;

public interface UserInfoDao {
    UserInfo selectByUserId(String userId);
    UserInfo selectByUserName(String userName);
    UserInfo selectByUserPhone(String userPhone);
    void insertUser(UserInfo userInfo);
    int updateUserInfo(UserInfo userInfo);
}
